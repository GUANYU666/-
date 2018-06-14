package com.example.hp.huanxin.widget;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.hp.huanxin.R;
import com.example.hp.huanxin.adapter.ContactAdapter;
import com.example.hp.huanxin.utils.StringUtils;
import com.hyphenate.util.DensityUtil;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Slidebar extends View {
    private  float avgHeight;
    private static final String[] SBCTIONS={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"
            ,"Q","R","S","T","U","V","W","X","Y","Z"};
    private int measuredHeight;
    private int measuredWidth;
    private Paint paint;
    private TextView text;
    private RecyclerView recyclerView;

    public Slidebar(Context context) {
        this(context,null);
    }
    public Slidebar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public Slidebar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                showToastAndScroll(event.getY());
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                break;
        }
        return true;
    }

    private void showToastAndScroll(float y) {
        if(text==null)
        {
            ViewGroup parent = (ViewGroup) getParent();
            text = ((TextView) parent.findViewById(R.id.tv_float));
            recyclerView = ((RecyclerView) parent.findViewById(R.id.contact_re));
        }
            text.setVisibility(VISIBLE);
           setBackgroundResource(R.drawable.slidebar_ba);
           int index = (int) (y / avgHeight);
           if(index<0)
           {
               index=0;
           }
           else if(index>SBCTIONS.length-1)
           {
               index = SBCTIONS.length-1;
           }
           String slideBarSection = SBCTIONS[index];

           text.setText(slideBarSection);


           ContactAdapter contactAdapter = (ContactAdapter) recyclerView.getAdapter();
           List<String> data = contactAdapter.getData();

        for (int i = 0; i < data.size(); i++) {
            if(StringUtils.getInit(data.get(i)).equals(slideBarSection))
            {
                recyclerView.smoothScrollToPosition(i);
                return;
            }
        }
//
//        RecyclerView.Adapter adapter = recyclerView.getAdapter();
//        if(!(adapter instanceof SectionIndexer))
//        {
//           return;
//        }
//        SectionIndexer sectionIndexer = (SectionIndexer) adapter;
//        String[] sections = (String[]) sectionIndexer.getSections();
//        int sectionIndex = Arrays.binarySearch(sections, slideBarSection);
//
//        if(sectionIndex<0)
//        {
//            return;
//        }
//
//        int positionForSection = sectionIndexer.getPositionForSection(sectionIndex);
//
//        recyclerView.scrollToPosition(positionForSection);
    }
    private void initview(Context context) {
         paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#8c8c8c"));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(DensityUtil.sp2px(getContext(),10));
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredHeight = getMeasuredHeight()-getPaddingBottom();
        measuredWidth = getMeasuredWidth();
         avgHeight = (measuredHeight+0f)/SBCTIONS.length;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = measuredWidth/2;
        for(int i=0;i<SBCTIONS.length;i++)
        {
            float y = avgHeight*(i+1);
            canvas.drawText(SBCTIONS[i],x,y,paint);
        }
    }
}