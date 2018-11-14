package org.richcode.koreatoron;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.richcode.koreatoron.Adapter.ToronLIstAdapter;
import org.richcode.koreatoron.CrawlingAsync.ToronPage;

public class FragmentToronBest extends Fragment {

    ListView ToronList;
    ToronLIstAdapter toronLIstAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    FragmentTransaction ft;

    @SuppressLint("ValidFragment")
    public FragmentToronBest() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_toronbest, container, false);

        ft = getFragmentManager().beginTransaction();

        ToronList = (ListView)layout.findViewById(R.id.besttoron_list);
        swipeRefreshLayout = (SwipeRefreshLayout)layout.findViewById(R.id.sr_best_layout);

        toronLIstAdapter = new ToronLIstAdapter();
        ToronList.setAdapter(toronLIstAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        new ToronPage(ToronList,toronLIstAdapter).execute(
                "https://www1.president.go.kr/forums?order=best",
                "div[class=ag_agTxt_area] h3",
                "div[class=ag_agTxt_area] div[class=ag_agtxt]",
                "div[class=ag_agTxt_area] ul",
                "div[class=ag_agTxt_area] ul[class=ag_agBtn] a[href^=https://www1.president.go.kr/forums/]",
                "ul[class=ag_agBtn] span",
                "div[class=agree] div[class=agree_gauge]",
                "div[class=against] div[class=against_gauge]"
        );
        new ToronPage(ToronList,toronLIstAdapter).execute(
                "https://www1.president.go.kr/forums?order=best&page=2",
                "div[class=ag_agTxt_area] h3",
                "div[class=ag_agTxt_area] div[class=ag_agtxt]",
                "div[class=ag_agTxt_area] ul",
                "div[class=ag_agTxt_area] ul[class=ag_agBtn] a[href^=https://www1.president.go.kr/forums/]",
                "ul[class=ag_agBtn] span",
                "div[class=agree] div[class=agree_gauge]",
                "div[class=against] div[class=against_gauge]"
        );
        new ToronPage(ToronList,toronLIstAdapter).execute(
                "https://www1.president.go.kr/forums?order=best&page=3",
                "div[class=ag_agTxt_area] h3",
                "div[class=ag_agTxt_area] div[class=ag_agtxt]",
                "div[class=ag_agTxt_area] ul",
                "div[class=ag_agTxt_area] ul[class=ag_agBtn] a[href^=https://www1.president.go.kr/forums/]",
                "ul[class=ag_agBtn] span",
                "div[class=agree] div[class=agree_gauge]",
                "div[class=against] div[class=against_gauge]"
        );

        ToronList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String href = toronLIstAdapter.getHref(i);

                Intent intent = new Intent(getContext(),WebBroswerActivity.class);
                intent.putExtra("link",href);

                startActivity(intent);
            }
        });

        return layout;
    }

    void Refresh(){
        ft.detach(this).attach(this).commit();
    }

}

