package me.jokey.movie.ui;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.jokey.movie.R;
import me.jokey.movie.model.MovieEntity;

/**
 * Created by wz on 2017/9/6 19:21.
 * desc:
 */
public class TopAdapter extends BaseQuickAdapter<MovieEntity, BaseViewHolder> {

    private Context mContext;

    public TopAdapter(Context context) {
        super(R.layout.item_recycler_top);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieEntity item) {
        Glide.with(mContext)
                .load(item.getImages().getLarge())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_text, item.getTitle())
                .setText(R.id.item_average, String.valueOf(item.getRating().getAverage()));

    }
}
