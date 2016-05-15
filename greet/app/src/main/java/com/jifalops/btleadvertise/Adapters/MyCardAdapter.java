package com.jifalops.btleadvertise.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jifalops.btleadvertise.Item.MyCardListViewItem;
import com.jifalops.btleadvertise.R;

import java.util.ArrayList;

/**
 * Created by client on 2016. 5. 13..
 */

public class MyCardAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<MyCardListViewItem> listViewItemList = null;
    private Bitmap mData;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;


    public MyCardAdapter() {
        listViewItemList = new ArrayList<MyCardListViewItem>();
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_mycard_list, parent, false);

            viewHolder.img = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
//        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView) ;


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        MyCardListViewItem listViewItem = listViewItemList.get(position);

        Log.d("MyCardAdapter", "position : " + position);
        Log.d("MyCardAdapter", "list size : " + listViewItemList.size());


        // 아이템 내 각 위젯에 데이터 반영
//        iconImageView.setImageBitmap(listViewItem.getImageView());

        Log.d("MyCardAdapter", "getImage : " + listViewItemList.get(position).getImageView());


        viewHolder.img.setImageBitmap(listViewItemList.get(position).getImageView());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Bitmap icon) {
        MyCardListViewItem item = new MyCardListViewItem();


        item.setImageView(getRoundedCornerBitmap(icon));

        Log.d("이리로 오긴오나", "왔어");
        listViewItemList.add(item);

        Log.d("MyCardAdapter", "list size : " + listViewItemList.size());


        notifyDataSetChanged();
   }

    public void removeItem(Bitmap icon)
    {
        listViewItemList.remove(icon);
    }


    public class ViewHolder {
        public ImageView img;
    }

    public Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 10;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
