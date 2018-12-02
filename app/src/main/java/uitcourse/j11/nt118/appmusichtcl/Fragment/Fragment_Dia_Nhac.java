package uitcourse.j11.nt118.appmusichtcl.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import uitcourse.j11.nt118.appmusichtcl.R;

public class Fragment_Dia_Nhac extends Fragment {

    View view;
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;
    Context context;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac,container,false);
        circleImageView = view.findViewById(R.id.imageviewcircle);

        // View thực hiện, tính chất, giá trị quay ban đầu , giá trị quay kết thúc
        objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        // Thời gian chạy
        objectAnimator.setDuration(10000);
        // Lập lại
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        // Không cho ngừng sau 1 lần chạy
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        return view;
    }

    public void Playnhac(String hinhanh) {

        Picasso.with(context).load(hinhanh).into(circleImageView);
    }
}
