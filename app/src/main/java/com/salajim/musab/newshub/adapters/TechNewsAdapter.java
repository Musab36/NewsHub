package com.salajim.musab.newshub.adapters;


public class TechNewsAdapter {
    /*
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<News> mNewses = new ArrayList<>();
    private Context mContext;

    public TechNewsAdapter.TechNewsViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        TechNewsViewHolder viewHolder = new TechNewsViewHolder();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TechNewsAdapter.TechNewsViewHolder holder, int position) {
        holder.bindTechNews(mNewses.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewses.size();
    }

    public class TechNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.newsImageView) ImageView mNewsImageView;
        @Bind(R.id.newsTitleTextView) TextView mNewsTitleTextView;
        @Bind(R.id.authorTextView) TextView mAuthorTextView;

        private Context mContext;

        public TechNewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindTechNews(News news) {
            Picasso.with(mContext)
                    .load(news.getUrlToImage())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mNewsImageView);

            mNewsTitleTextView.setText(news.getTitle());
            mAuthorTextView.setText("By: " + news.getAuthor());
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent()
        }
    }
    */
}
