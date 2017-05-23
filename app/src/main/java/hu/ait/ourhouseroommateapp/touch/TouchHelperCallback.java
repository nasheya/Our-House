package hu.ait.ourhouseroommateapp.touch;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by nasheyarahman on 5/21/17.
 */


public class TouchHelperCallback extends
        ItemTouchHelper.Callback {

    private TouchHelperInterface touchHelperAdapter;

    public TouchHelperCallback(TouchHelperInterface adapter) {
        this.touchHelperAdapter = adapter;
    }

//    @Override
//    public boolean isLongPressDragEnabled() {
//        return true;
//    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        //touchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(),
        //        target.getAdapterPosition()
        //);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        touchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
