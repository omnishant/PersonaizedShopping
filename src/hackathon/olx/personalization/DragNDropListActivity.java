/*
 * Copyright (C) 2010 Eric Harlow
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hackathon.olx.personalization
;

import hackathon.olx.dragndrop.DragListener;
import hackathon.olx.dragndrop.DragNDropListView;
import hackathon.olx.dragndrop.DropListener;
import hackathon.olx.dragndrop.RemoveListener;
import hackathon.olx.models.HomePageListing;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DragNDropListActivity extends ListActivity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dragndroplistview);
        
        prepareDragNDropListView();
//        ArrayList<String> content = new ArrayList<String>(mListContent.length);
//        for (int i=0; i < mListContent.length; i++) {
//        	content.add(mListContent[i]);
//        }
//        
//        ListView listView = getListView();
//        
//        if (listView instanceof DragNDropListView) {
//        	((DragNDropListView) listView).setDropListener(mDropListener);
//        	((DragNDropListView) listView).setRemoveListener(mRemoveListener);
//        	((DragNDropListView) listView).setDragListener(mDragListener);
//        }
    }
    
    private List<HomePageListing> populateListingData(Context context){
    	ArrayList<HomePageListing> adapter = new ArrayList<HomePageListing>();
    	Resources resources = context.getResources();
    	HomePageListing listing1 = new HomePageListing();
    	
    	listing1.setmBmp(BitmapFactory.decodeResource(resources,R.drawable.cars));
    	listing1.setmDescription("loremIpsum DOlor sit");
    	adapter.add(listing1);
    	listing1 = new HomePageListing();
    	listing1.setmBmp(BitmapFactory.decodeResource(resources,R.drawable.mobiles));
    	listing1.setmDescription("lore mIpsum DOlor sit");
    	adapter.add(listing1);

    	listing1 = new HomePageListing();
    	listing1.setmBmp(BitmapFactory.decodeResource(resources,R.drawable.electronics));
    	listing1.setmDescription("lore mIpsum DOlor sit"); 	
    	adapter.add(listing1);
    	return adapter;

    }
    
    private void prepareDragNDropListView(){
    	Context context = getApplicationContext();
    	List<HomePageListing> items = populateListingData(context);
    	DragNDropAdapter adapter = new DragNDropAdapter(context, items);
    	DragNDropListView listView = (DragNDropListView) getListView();
    	listView.setAdapter(adapter);
    	ListingDragListener listener = new ListingDragListener(context, adapter, listView);
    	listView.setDropListener(listener);
    	listView.setDragListener(listener);
    	listView.setRemoveListener(listener);
    }

	private DropListener mDropListener = 
		new DropListener() {
        public void onDrop(int from, int to) {
        	ListAdapter adapter = getListAdapter();
        	if (adapter instanceof DragNDropAdapter) {
        		((DragNDropAdapter)adapter).onDrop(from, to);
        		getListView().invalidateViews();
        	}
        }
    };
    
    private RemoveListener mRemoveListener =
        new RemoveListener() {
        public void onRemove(int which) {
        	ListAdapter adapter = getListAdapter();
        	if (adapter instanceof DragNDropAdapter) {
        		((DragNDropAdapter)adapter).onRemove(which);
        		getListView().invalidateViews();
        	}
        }
    };
    
    private DragListener mDragListener =
    	new DragListener() {

    	int backgroundColor = 0xe0103010;
    	int defaultBackgroundColor;
    	
			public void onDrag(int x, int y, ListView listView) {
				// TODO Auto-generated method stub
			}

			public void onStartDrag(View itemView) {
				itemView.setVisibility(View.INVISIBLE);
				defaultBackgroundColor = itemView.getDrawingCacheBackgroundColor();
				itemView.setBackgroundColor(backgroundColor);
				ImageView iv = (ImageView)itemView.findViewById(R.id.ImageView01);
				if (iv != null) iv.setVisibility(View.INVISIBLE);
			}

			public void onStopDrag(View itemView) {
				itemView.setVisibility(View.VISIBLE);
				itemView.setBackgroundColor(defaultBackgroundColor);
				ImageView iv = (ImageView)itemView.findViewById(R.id.ImageView01);
				if (iv != null) iv.setVisibility(View.VISIBLE);
			}
    	
    };
    
    private static String[] mListContent={"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7"};
}