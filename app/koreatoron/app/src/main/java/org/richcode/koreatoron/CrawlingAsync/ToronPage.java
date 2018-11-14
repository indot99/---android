package org.richcode.koreatoron.CrawlingAsync;

import android.os.AsyncTask;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.richcode.koreatoron.Adapter.ToronLIstAdapter;
import org.richcode.koreatoron.ModelClass.ToronModel;

import java.io.IOException;

public class ToronPage extends AsyncTask<String,Void,String> {

    ListView listView;
    ToronLIstAdapter infoListAdapter;

    public ToronPage(ListView listView, ToronLIstAdapter infoListAdapter) {
        this.listView = listView;
        this.infoListAdapter = infoListAdapter;
        this.listView.setAdapter(this.infoListAdapter);
    }

    @Override
    protected String doInBackground(String... params) {

        String URL = params[0];
        String title = params[1];
        String content = params[2];
        String date = params[3];
        String href = params[4];
        String comment_count = params[5];
        String true_count = params[6];
        String false_count = params[7];
        String result = "";

        try {
            org.jsoup.nodes.Document document = Jsoup.connect(URL).get();
            //Elements elements = document.select(title);
            Elements Title = document.select(title);
            Elements Content = document.select(content);
            Elements Date = document.select(date).not("ul[class=ag_agBtn]");
            Elements Href = document.select(href);
            Elements CommentCount = document.select(comment_count).not("span[class=sound_only]");
            Elements TrueCount = document.select(true_count);
            Elements FalseCount = document.select(false_count);


            for (int i = 0; i<Title.size(); i++){
                String title_string = Title.get(i).text();
                String content_string = Content.get(i).text();
                String date_string = Date.get(i).text();
                String href_string = Href.get(i).attr("abs:href");
                String comment_count_string = CommentCount.get(i).text();
                String true_count_string = TrueCount.get(i).text();
                String false_count_string = FalseCount.get(i).text();

                //String comment_count_string = "dd";
//                String true_count_string = "dd";
//                String false_count_string = "dd";

                infoListAdapter.add(new ToronModel(
                        title_string,
                        true_count_string,
                        false_count_string,
                        date_string,
                        content_string,
                        comment_count_string,
                        href_string));
            }



            return result;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        infoListAdapter.notifyDataSetChanged();
    }
}
