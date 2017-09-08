package me.jokey.movie.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.jokey.movie.R;
import me.jokey.movie.model.PersonageEntity;

/**
 * Created by wz on 2017/9/7 19:57.
 * desc:
 */
public class PersonageAdapter extends BaseQuickAdapter<PersonageEntity, BaseViewHolder> {

    private Context mContext;

    public PersonageAdapter(Context context, @Nullable List<PersonageEntity> data) {
        super(R.layout.item_recycler_personage, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonageEntity item) {
        Glide.with(mContext)
                .load(item.getAvatars().getLarge())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView) helper.getView(R.id.item_avatars));
        helper.setText(R.id.item_name, item.getName());
    }
}
