package uitcourse.j11.nt118.appmusichtcl.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uitcourse.j11.nt118.appmusichtcl.Adapter.SearchBaiHatAdapter;
import uitcourse.j11.nt118.appmusichtcl.Model.Baihat;
import uitcourse.j11.nt118.appmusichtcl.R;
import uitcourse.j11.nt118.appmusichtcl.Service.APIService;
import uitcourse.j11.nt118.appmusichtcl.Service.Dataservice;

public class Fragment_Danhsachbaihat_TimKiem extends Fragment {

    View view;
    RecyclerView recyclerViewsearchbaihat;
    TextView txtkhongcodulieu;
    SearchBaiHatAdapter searchBaiHatAdapter;
    String query;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danhsachbaihat_timkiem,container,false);
        recyclerViewsearchbaihat = view.findViewById(R.id.recyclerviewsearchbaihat);
        txtkhongcodulieu = view.findViewById(R.id.textviewkhongcodulieu);
        SearchTuKhoaBaiHat(query);
        return view;

    }

    public void SearchTuKhoaBaiHat(String query)
    {
        Log.d("TUONGTESTSEARCH",query);
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
                    recyclerViewsearchbaihat.setVisibility(View.VISIBLE);


                }
                else
                {
                    Log.d("Khongtimthay","Khongtimthay");
                    recyclerViewsearchbaihat.setVisibility(View.GONE);
                    txtkhongcodulieu.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
