package hackathon.olx.personalization;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import hackathon.olx.dragndrop.DragListener;
import hackathon.olx.dragndrop.DragNDropListView;
import hackathon.olx.dragndrop.DropListener;
import hackathon.olx.dragndrop.RemoveListener;

public class ListingDragListener implements DragListener,DropListener,RemoveListener {

	DragNDropAdapter mAdapter;
	DragNDropListView mList;
	private Context mContext;

	public ListingDragListener(Context context,DragNDropAdapter adapter,DragNDropListView list) {
		mAdapter = adapter;
		mContext = context;
		mList = list;
	}
	@Override
	public void onRemove(int which) {
		mAdapter.onRemove(which);
		mList.invalidateViews();		
	}

	@Override
	public void onDrop(int from, int to) {
    	mAdapter.onDrop(from, to);
    	mList.invalidateViews();		
	}
	@Override
	public void onStartDrag(View itemView) {
		itemView.setVisibility(View.INVISIBLE);
		//itemView.setBackground(mContext.getResources().getDrawable(R.drawable.drag));
	}

	@Override
	public void onDrag(int x, int y, ListView listView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopDrag(View itemView) {
		itemView.setVisibility(View.VISIBLE);
		//itemView.setBackgroundColor(defaultBackgroundColor);
		ImageView iv = (ImageView)itemView.findViewById(R.id.ImageView01);
		if (iv != null) iv.setVisibility(View.VISIBLE);
	}

}
