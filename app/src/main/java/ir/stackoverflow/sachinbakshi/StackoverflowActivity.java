package ir.stackoverflow.sachinbakshi;


import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.stackoverflow.sachinbakshi.Adapter.CustomRecylerListAdapterr;
import ir.stackoverflow.sachinbakshi.Data.QuestionColumns;
import ir.stackoverflow.sachinbakshi.Data.QuestionProvider;
import ir.stackoverflow.sachinbakshi.Model.Question;
import ir.stackoverflow.sachinbakshi.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StackoverflowActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    @Bind(R.id.tagged)
    EditText tagged;
    @Bind(R.id.getData)
    Button getData;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.list2)
    ListView listView;
    private static final int CURSOR_LOADER_ID = 0;

    private static final String LOG_TAG = StackoverflowActivity.class.getSimpleName();

    private ArrayList<ListItemModel> mListData = new ArrayList<>();;
    private CustomRecylerListAdapterr adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stackoverflow);
        ButterKnife.bind(this);
      //  getSupportLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        adapter = new CustomRecylerListAdapterr(this, mListData);
        listView.setAdapter(adapter);
    }


    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "resume called");

     //  getSupportLoaderManager().restartLoader(CURSOR_LOADER_ID, null, this);
    }
/*
    @OnClick(R.id.likeButton)
    void insert(){

        Toast.makeText(StackoverflowActivity.this, "You clicked", Toast.LENGTH_LONG).show();


    }*/


    @OnClick(R.id.getData)
    void getDataFunction() {
        getData.setText("Waiting...");
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.stackexchange.com/2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        /*int pageVar = Integer.parseInt(page.getText().toString());
        int pagesizeVar = Integer.parseInt(pagesize.getText().toString());
        String orderVar = order.getText().toString();
        String sortVar = sort.getText().toString();
        String taggedVar = tagged.getText().toString();
        String siteVar = site.getText().toString();*/
        String taggedVar = tagged.getText().toString();
        Call<Question> call = apiService.getQuestionsService(taggedVar);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                getData.setText("Get Data");
                progressBar.setVisibility(View.INVISIBLE);
                String responseString = "Response: ";
               // String tag = "";
                ListItemModel listItem;
                try {
                    if (response.isSuccess()) {
                        Toast.makeText(StackoverflowActivity.this, "Success", Toast.LENGTH_LONG).show();

                        Question question = response.body();
                        responseString += "\nSize Of Questions: " + question.getItems().size();
                        mListData.clear();
                        if (question.getItems()!=null && question.getItems().size() > 0)
                            for(int i=0;i<question.getItems().size();i++) {
                                listItem = new ListItemModel();
                                int quotaRemaining = question.getQuotaRemaining();
                                listItem.setQuotaRemaining(quotaRemaining);
                                int quoataMax = question.getQuotaMax();
                                listItem.setQuotaMax(quoataMax);
                                String tag="";
                                for(int j=0; j<question.getItems().get(i).getTags().size();j++)
                                 tag += question.getItems().get(i).getTags().get(j)+ " ";
                                listItem.setTags(tag);
                                String profiePic = question.getItems().get(i).getOwner().getProfileImage();
                               listItem.setUserProfileImage(profiePic);
                                String displayName = question.getItems().get(i).getOwner().getDisplayName();
                                listItem.setUserDisplayName(displayName);
                                int score = question.getItems().get(i).getScore();
                                listItem.setScore(score);
                                int lastactivity = question.getItems().get(i).getLastActivityDate();
                                listItem.setLastActivity(lastactivity);
                                String questionLink = question.getItems().get(i).getLink();
                                listItem.setQuestionLink(questionLink);
                                String questionTitle = question.getItems().get(i).getTitle();
                                listItem.setQuestionTitle(questionTitle);
                                responseString += "\nFirst item title: " + question.getItems().get(0).getTitle();
                                mListData.add(listItem);
                            }
                        insertData();
                       // responseTextView.setText(responseString);
                    } else {
                        responseString += "\nFailed! : " + response.errorBody().string();
                        //responseTextView.setText(responseString);
                    }
                } catch (IOException e) {
                    Log.e("bkhezry", "IOException:"+e.getMessage().toString());
                }

            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                getData.setText("Get Data");
                progressBar.setVisibility(View.INVISIBLE);
                if (t != null) {
                    //responseTextView.setText("Response:\nOnFailure: " + t.getMessage());
                }
            }
        });
    }



    public void insertData(){
        Log.d(LOG_TAG, "insert");
        ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>(mListData.size());

        for (ListItemModel planet : mListData){
            ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                    QuestionProvider.Questions.CONTENT_URI);
            builder.withValue(QuestionColumns.QUOTA_REMAINING, planet.getQuotaRemaining());
            builder.withValue(QuestionColumns.QUOTA_MAX, planet.getQuotaMax());
            builder.withValue(QuestionColumns.USER_PROFILE_IMAGE, planet.getUserProfileImage());
            builder.withValue(QuestionColumns.USER_DISPLAY_NAME, planet.getUserDisplayName());
            builder.withValue(QuestionColumns.SCORE, planet.getScore());
            builder.withValue(QuestionColumns.QUESTION_LINK, planet.getQuestionLink());
            builder.withValue(QuestionColumns.QUESTION_TITLE, planet.getQuestionTitle());
            builder.withValue(QuestionColumns.LAST_ACTIVITY, planet.getLastActivity());
            builder.withValue(QuestionColumns.TAGS, planet.getTags());
            batchOperations.add(builder.build());
        }

        try{
            this.getContentResolver().applyBatch(QuestionProvider.AUTHORITY, batchOperations);
        } catch(RemoteException | OperationApplicationException e){
            Log.e(LOG_TAG, "Error applying batch insert", e);

        }

    }





    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args){
        return new CursorLoader(this, QuestionProvider.Questions.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        /*// mCursorAdapter.swapCursor(data);
        if(downloadType!=null && downloadType.equalsIgnoreCase((getString(R.string.type_favourite)))) {
            Toast.makeText(getActivity(), "Holoaaaaa HAHAAH", Toast.LENGTH_LONG).show();
            mProgressBar.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.VISIBLE);
            data.moveToFirst();
            GridItem item;
            mGridData.clear();
            while (!data.isAfterLast()) {

                item = new GridItem();
                String poster = data.getString(data.getColumnIndex(
                        FavouriteMovieColumns.POSTER_PATH));
                item.setPoster_path(poster);
                String backdrop = data.getString(data.getColumnIndex(
                        FavouriteMovieColumns.BACK_DROP));
                item.setBackdrop_path(backdrop);
                String title = data.getString(data.getColumnIndex(
                        FavouriteMovieColumns.TITLE));
                item.setOriginal_title(title);
                String overview = data.getString(data.getColumnIndex(
                        FavouriteMovieColumns.OVERVIEW));
                item.setOverview(overview);
                double voteAverage = data.getDouble(data.getColumnIndex(
                        FavouriteMovieColumns.VOTE_AVERAGE));
                String releaseDate = data.getString(data.getColumnIndex(
                        FavouriteMovieColumns.RELEASE_DATE));
                item.setRelease_date(releaseDate);
                mGridData.add(item);




                data.moveToNext();
            }
            mGridAdapter = new GridViewAdapter(mGridData,getActivity() );
            rv.setAdapter(mGridAdapter);
*/
        }



    @Override
    public void onLoaderReset(Loader<Cursor> loader){
       /* // mCursorAdapter.swapCursor(null);
        mGridAdapter = new GridViewAdapter(mGridData,getActivity() );
        rv.setAdapter(mGridAdapter);*/
    }
}
