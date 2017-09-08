package ru.muzis.muzistest.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.muzis.muzistest.R;
import ru.muzis.muzistest.model.ArtistModel;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.InvalidatableViewHolder> {
    private static final int TYPE_ARTIST = 0;

    private Context mContext;
    private List<ArtistModel> mArtistList;
    private LayoutInflater mInflater;

    public ChartAdapter(Context context, List<ArtistModel> artistList) {
        mContext = context;
        mArtistList = artistList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ChartAdapter.InvalidatableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ARTIST:
                return new ArtistViewHolder(mInflater.inflate(R.layout.item_artist, parent, false));
        }
        throw new IllegalArgumentException("Unknown viewType");
    }

    @Override
    public void onBindViewHolder(ChartAdapter.InvalidatableViewHolder holder, int position) {
        ((ArtistViewHolder) holder).invalidate(mArtistList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArtistList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ARTIST;
    }

    public static abstract class InvalidatableViewHolder<T> extends RecyclerView.ViewHolder {
        public InvalidatableViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void invalidate(T model);
    }

    public static class ArtistViewHolder extends InvalidatableViewHolder<ArtistModel> {
        @BindView(R.id.item_artist_title)
        public TextView title;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void invalidate(ArtistModel model) {
            int trackCount = model.getTracks() == null ? 0 : model.getTracks().size();
            title.setText(model.getName() + " " + trackCount);
        }
    }
}
