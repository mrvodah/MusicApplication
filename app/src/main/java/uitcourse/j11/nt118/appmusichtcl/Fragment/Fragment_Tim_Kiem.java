package uitcourse.j11.nt118.appmusichtcl.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uitcourse.j11.nt118.appmusichtcl.Adapter.SearchAlbumAdapter;
import uitcourse.j11.nt118.appmusichtcl.Adapter.SearchBaiHatAdapter;
import uitcourse.j11.nt118.appmusichtcl.Adapter.SearchPlaylistAdapter;
import uitcourse.j11.nt118.appmusichtcl.Model.Album;
import uitcourse.j11.nt118.appmusichtcl.Model.Baihat;
import uitcourse.j11.nt118.appmusichtcl.Model.Playlist;
import uitcourse.j11.nt118.appmusichtcl.R;
import uitcourse.j11.nt118.appmusichtcl.Service.APIService;
import uitcourse.j11.nt118.appmusichtcl.Service.Dataservice;

public class Fragment_Tim_Kiem extends Fragment {

    View view;
    Toolbar toolbar;
    RecyclerView recyclerViewsearchbaihat, recyclerViewsearchalbum,recyclerViewsearchplaylist;
    TextView txtkhongcodulieu,txtkhongcodulieualbum,txtkhongcodulieuplaylist,txtmoresongssearch,txtmorealbumsearch,txtmoreplaylistsearch;
    SearchBaiHatAdapter searchBaiHatAdapter;// Đưa danh sách kết quả kiếm được lên RecyclerView
    SearchAlbumAdapter searchAlbumAdapter;
    SearchPlaylistAdapter searchPlaylistAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar = view.findViewById(R.id.toolbarsearchbaihat);
        recyclerViewsearchbaihat = view.findViewById(R.id.recyclerviewsearchbaihat);
        recyclerViewsearchalbum = view.findViewById(R.id.recyclerviewsearchAlbum);
        recyclerViewsearchplaylist = view.findViewById(R.id.recyclerviewsearchPlaylist);
        txtkhongcodulieu = view.findViewById(R.id.textviewkhongcodulieu);
        txtkhongcodulieualbum = view.findViewById(R.id.textviewkhongcodulieualbum);
        txtkhongcodulieuplaylist = view.findViewById(R.id.textviewkhongcodulieuPlaylist);
        txtmoresongssearch = view.findViewById(R.id.textviewmoresongssearch);
        txtmorealbumsearch = view.findViewById(R.id.textviewmorealbumsearch);
        txtmoreplaylistsearch = view.findViewById(R.id.textviewmoreplaylistsearch);


        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("TUONGTESTSEARCH",query);
                SearchTuKhoaBaiHat(query);
                SearchTuKhoaAlbum(query);
                SearchTuKhoaPlaylist(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchTuKhoaAlbum(String query) {

        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetSearchAlbum(query);
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum = (ArrayList<Album>) response.body();
                for(int i=0; i<mangalbum.size();i++)
                    Log.d("Testmangalbum",mangalbum.get(i).getTenAblum());

                if(mangalbum.size()>0)
                {
                    Log.d("Testmangalbum",String.valueOf(mangalbum.size()));
                    searchAlbumAdapter = new SearchAlbumAdapter(getActivity(),mangalbum);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewsearchalbum.setLayoutManager(linearLayoutManager);
                    recyclerViewsearchalbum.setAdapter(searchAlbumAdapter);
                    txtkhongcodulieualbum.setVisibility(View.GONE);
                    //txtmorealbumsearch.setVisibility(View.VISIBLE);
                    recyclerViewsearchalbum.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtmorealbumsearch.setVisibility(View.GONE);
                    recyclerViewsearchalbum.setVisibility(View.GONE);
                    txtkhongcodulieualbum.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });


    }


    private void SearchTuKhoaBaiHat(String query)
    {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetSearchBaihat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {

                ArrayList<Baihat> mangbaihat = (ArrayList<Baihat>) response.body();
                //Log.d("TuongSearch",mangbaihat.get(0).getTenBaiHat());

                if(mangbaihat.size()>0)
                {
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(),mangbaihat);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewsearchbaihat.setLayoutManager(linearLayoutManager);
                    recyclerViewsearchbaihat.setAdapter(searchBaiHatAdapter);


                    // Nếu có dữ liệu
                    txtkhongcodulieu.setVisibility(View.GONE);
                    //txtmoresongssearch.setVisibility(View.VISIBLE);
                    recyclerViewsearchbaihat.setVisibility(View.VISIBLE);

                }
                else
                {
                    Log.d("Khongtimthay","Khongtimthay");
                    txtmoresongssearch.setVisibility(View.GONE);
                    recyclerViewsearchbaihat.setVisibility(View.GONE);
                    txtkhongcodulieu.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
            }
        });
    }

    private void SearchTuKhoaPlaylist(String query) {

        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetSearchPlaylist(query);
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                for(int i=0; i<mangplaylist.size();i++)
                    Log.d("Testmangplaylist",mangplaylist.get(i).getTen());

                if(mangplaylist.size()>0)
                {
                    Log.d("Testmangplaylist",String.valueOf(mangplaylist.size()));
                    searchPlaylistAdapter = new SearchPlaylistAdapter(getActivity(),mangplaylist);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewsearchplaylist.setLayoutManager(linearLayoutManager);
                    recyclerViewsearchplaylist.setAdapter(searchPlaylistAdapter);
                    txtkhongcodulieuplaylist.setVisibility(View.GONE);
                    //txtmoreplaylistsearch.setVisibility(View.VISIBLE);
                    recyclerViewsearchplaylist.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtmoreplaylistsearch.setVisibility(View.GONE);
                    recyclerViewsearchplaylist.setVisibility(View.GONE);
                    txtkhongcodulieuplaylist.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}
