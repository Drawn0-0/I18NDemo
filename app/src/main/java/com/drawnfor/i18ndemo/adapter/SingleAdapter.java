package com.drawnfor.i18ndemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.drawnfor.i18ndemo.R;
import com.drawnfor.i18ndemo.bean.LanguageBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.VH> {

    private final Context context;
    private LanguageBean mSelectedBean;
    private final List<LanguageBean> mDataList;
    private int lastSelectIndex;

    public SingleAdapter(Context context, List<LanguageBean> dataList, LanguageBean selectedBean) {
        this.context = context;
        this.mDataList = dataList;
        this.mSelectedBean = selectedBean;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_single, parent, false);
        return new VH(view);
    }

    public LanguageBean getSelectBean() {
        return mSelectedBean;
    }


    @Override
    public void onBindViewHolder(@NonNull VH vh, final int position) {
        LanguageBean bean = mDataList.get(position);
        vh.tvContent.setText(bean.getName());
        if (bean.equals(mSelectedBean)) {
            vh.cbSelect.setChecked(true);
            lastSelectIndex = position;
        } else {
            vh.cbSelect.setChecked(false);
        }

        vh.divider.setVisibility(position == mDataList.size() - 1 ? View.GONE : View.VISIBLE);
        vh.itemView.setOnClickListener(view -> {
            if (!bean.equals(mSelectedBean)) {
                mSelectedBean = bean;
                notifyItemChanged(lastSelectIndex);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final TextView tvContent;
        private final CheckBox cbSelect;
        private final View divider;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.item_single_select_tv_name);
            cbSelect = itemView.findViewById(R.id.item_single_select_cb_select);
            divider = itemView.findViewById(R.id.item_single_select_view_divider);
        }
    }
}
