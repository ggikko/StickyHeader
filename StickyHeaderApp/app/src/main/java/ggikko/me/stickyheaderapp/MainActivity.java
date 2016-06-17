package ggikko.me.stickyheaderapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ChangeInterface {

    @BindView(R.id.scroll1) CustomScrollView scroll1;
    @BindView(R.id.scroll2) CustomScrollView scroll2;

    @BindView(R.id.header_text1) TextView header_text1;
    @BindView(R.id.header_text2) TextView header_text2;
    @BindView(R.id.header_text3) TextView header_text3;
    @BindView(R.id.header_text4) TextView header_text4;

    @BindView(R.id.content_header_text2) TextView content_header_text2;
    @BindView(R.id.content_header_text3) TextView content_header_text3;
    @BindView(R.id.content_header_text4) TextView content_header_text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @Override
    public void scrollChanged(int yValue) {

    }

    public void setScrollYValue(int scrollY) {

        float header_text1_Height = header_text1.getHeight();
        float content_header_text2_yValue = content_header_text2.getY();
        float content_header_text3_yValue = content_header_text3.getY();
        float content_header_text4_yValue = content_header_text4.getY();

        Log.e("ggikko","kkk : " + (scrollY - content_header_text2_yValue));

        //첫번째 헤더 위치 고정 - 두번째 헤더가 view 최상단에 위치하지 않을 때
        if(scrollY - content_header_text2_yValue < 0) setHeaderLocation(header_text1_Height, header_text1,header_text2,header_text3,header_text4 );

        if(scrollY - content_header_text2_yValue > 0 && scrollY - content_header_text2_yValue < header_text1_Height){
            header_text1.setY(-(scrollY - content_header_text2_yValue));
            header_text2.setY(-(scrollY - content_header_text2_yValue) + header_text1_Height);
        }

        //두번째 헤더 위치 고정 - scroll이 두번째 해더와 세번째 헤더 사이에 있을 때
        if(scrollY - content_header_text2_yValue - header_text1_Height > 0 && scrollY - content_header_text3_yValue < 0) setHeaderLocation(header_text1_Height, header_text2,header_text1,header_text3,header_text4 );

        //위와 동일
        if(scrollY - content_header_text3_yValue > 0 && scrollY - content_header_text3_yValue < header_text1_Height) {
            header_text2.setY(-(scrollY - content_header_text3_yValue));
            header_text3.setY(-(scrollY - content_header_text3_yValue) + header_text1_Height);
        }

        //위와 동일
        if(scrollY - content_header_text3_yValue - header_text1_Height > 0 && scrollY - content_header_text4_yValue < 0) setHeaderLocation(header_text1_Height, header_text3,header_text1,header_text2,header_text4 );

        //위와 동일
        if(scrollY - content_header_text4_yValue > 0 && scrollY - content_header_text4_yValue < header_text1_Height) {
            header_text3.setY(-(scrollY - content_header_text4_yValue));
            header_text4.setY(-(scrollY - content_header_text4_yValue) + header_text1_Height);
        }

        //위와 동일
        if(scrollY - content_header_text4_yValue - header_text1_Height > 0) setHeaderLocation(header_text1_Height, header_text4,header_text1,header_text2,header_text3 );

    }

    private void setHeaderLocation(float height, TextView... textViews){
        for(TextView textView : textViews) textView.setY(height);
        textViews[0].setY(0);
    }

    /** 화면 해상도에 따른 변화 대응 */
    private float dpToPx(float yValue){
        final float scale = getResources().getDisplayMetrics().density;
        return yValue * scale + 0.5f;
    }

    private float pxToDp(float yValue){
        final float scale = getResources().getDisplayMetrics().density;
        return (yValue - 0.5f)/scale;
    }

}
