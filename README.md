# RcvBaseAdapter
An base adapter for android recyclerview

-----------------

> This is an Adapter for RecyclerView, contains some usual methods,such as setText(), setImageResource() etc.


##Get it
* Gradle
```
compile 'com.sakurawood:rcvbaseadapter:0.1.6'
```
* Maven
```
<dependency>
  <groupId>com.sakurawood</groupId>
  <artifactId>rcvbaseadapter</artifactId>
  <version>0.1.6</version>
  <type>pom</type>
</dependency>
```
* Ivy
```
<dependency org='com.sakurawood' name='rcvbaseadapter' rev='0.1.6'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>
```

##Setup
* Initialize like this:
```java
  RcvBaseAdapter = new RcvBaseAdapter<Bean>(getApplicationContext(), R.layout.item, list) {

            @Override
            protected void convert(BaseViewHolder holder, Bean item ,int position) {
                holder.setText(R.id.name, item.getName());
                holder.setText(R.id.value, item.getValue());
                holder.setImageResource(R.id.img, item.getPic());
                holder.setOnClickListener(R.id.img, new RcvBaseAdapter.OnItemChildClickListener());
                //add an adapter of a child recyclerview...
                holder.setAdapter(R.id.recylerview, madpter, new GridLayoutManager(getApplicationContext(),3));
                holder.setOnClickListener(R.id.img, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    //change your list bean by the position...
                        if (!list.get(position).isBl()) {
                            list.get(position).setPic(R.drawable.img_9);
                            list.get(position).setBl(true);
                        } else {
                            list.get(position).setPic(R.drawable.sakura);
                            list.get(position).setBl(false);
                        }
                        notifyDataSetChanged();
                    }
                });
            }
  };

```

* Add a clicklistener for RecyclerView:
```java
//add listener of item view 
  RcvBaseAdapter.setOnRecyclerViewItemClickListener(new RcvBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, "haha" + "   " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClick(View view, int position) {
                return false;
            }
  });
//add listener of item child view 
  RcvBaseAdapter.setOnRecyclerViewChildItemClickListener(new RcvBaseAdapter.OnRecyclerViewChildItemClickListener() {
            @Override
            public void onChildClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.img:
                        Toast.makeText(MainActivity.this,
                                position + "    " + view.getId(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public boolean onChildLongClick(View view, int position) {
                return false;
            }
  });
```
* Add a swipe or drag listener of item:
```java
 dscb = new DragSwipeCallback(RcvBaseAdapter);
        dscb.setDragEnable(true);
        dscb.setSwipeEnable(true);
        ItemTouchHelper touchHelper = new ItemTouchHelper(dscb);
        touchHelper.attachToRecyclerView(recyclerView);
```
