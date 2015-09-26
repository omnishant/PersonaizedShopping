
package hackathon.olx.personalization;

import hackathon.olx.dragndrop.DropListener;
import hackathon.olx.dragndrop.RemoveListener;
import hackathon.olx.models.HomePageListing;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public final class DragNDropAdapter extends BaseAdapter implements RemoveListener, DropListener{

	List<HomePageListing> mItems;
    private LayoutInflater mInflater;

    public DragNDropAdapter(Context context, List<HomePageListing> items) {
    	init(context, items);
    }
    
    private void init(Context context, List<HomePageListing> items) {
    	// Cache the LayoutInflate to avoid asking for a new one each time.
    	mInflater = LayoutInflater.from(context);
    	mItems = items;
    }
    
    /**
     * The number of items in the list
     * @see android.widget.ListAdapter#getCount()
     */
    public int getCount() {
        return mItems.size();
    }

    /**
     * Since the data comes from an array, just returning the index is
     * sufficient to get at the data. If we were using a more complex data
     * structure, we would return whatever object represents one row in the
     * list.
     *
     * @see android.widget.ListAdapter#getItem(int)
     */
    public HomePageListing getItem(int position) {
        return mItems.get(position);
    }

    /**
     * Use the array index as a unique id.
     * @see android.widget.ListAdapter#getItemId(int)
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Make a view to hold each row.
     *
     * @see android.widget.ListAdapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unneccessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.top_categories_list_item, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.mDescription = (TextView) convertView.findViewById(R.id.top_listing_item_desc);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.top_listing_item_image);
            
            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind the data efficiently with the holder.
        holder.mDescription.setText(mItems.get(position).getmDescription());
        holder.mImageView.setImageBitmap(mItems.get(position).getmBmp());
        return convertView;
    }

    static class ViewHolder {
        TextView mDescription;
        ImageView mImageView;
    }

	public void onRemove(int which) {
		if (which < 0 || which > mItems.size()) return;		
		mItems.remove(which);
	}

	public void onDrop(int from, int to) {
		HomePageListing temp = mItems.get(from);
		mItems.remove(from);
		mItems.add(to,temp);
		
	}
}