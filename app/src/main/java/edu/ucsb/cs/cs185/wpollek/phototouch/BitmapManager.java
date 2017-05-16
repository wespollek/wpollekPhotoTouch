package edu.ucsb.cs.cs185.wpollek.phototouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wesley on 2/27/2017.
 */

public class BitmapManager {
    public static List<Bitmap> ITEMS = new ArrayList<Bitmap>();
    public static int count = 0;
    //    public static final Map<String, MyBitmap> ITEM_MAP = new HashMap<String, MyBitmap>();
    public interface BitListener{
        void onUpdate();
    }
    public static BitListener listener;
    public static void addItem(Bitmap item) {
        ITEMS.add(item);
        listener.onUpdate();
        count = count +1;
    }

    public static Bitmap getBitmap(int i){
        Bitmap bm = ITEMS.get(i);
        return bm;
    }
    public static void removeItem(Bitmap item){
        ITEMS.remove(item);
        count = count-1;
        listener.onUpdate();
    }
}
