package com.java.group8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by xzwkl on 17/9/9.
 */

public class NewsPageActivity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;

    public NewsPageActivity() {
        super();
    }

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_newspage);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);

        ActionBar ab = getSupportActionBar();
        //使能app bar的导航功能
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_newspage_opinions, menu);

        MenuItem read_newspage = menu.findItem(R.id.read_newspage);
        MyReadActionProvider mActionProvider = (MyReadActionProvider) MenuItemCompat.getActionProvider(read_newspage);

        MenuItem share_newspage = menu.findItem(R.id.share_newspage);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(share_newspage);
//        MenuItem.OnMenuItemClickListener omicl = mActionProvider;
//        read_newspage.setOnMenuItemClickListener(omicl);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        setShareIntent(sendIntent);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_newspage:
                break;
            case R.id.favor_newspage:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
class MyReadActionProvider extends ActionProvider {

    private Context context;

//    public boolean onMenuItemClick(MenuItem item) {
//        return true;
//    }

    public MyReadActionProvider(Context context) {
        super(context);
    }

    public View onCreateActionView() {
        return null;
    }
    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();

        subMenu.add("sub item 1")
                .setIcon(R.mipmap.ic_launcher)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        return true;
                    }
                });
        subMenu.add("sub item 2")
                .setIcon(R.mipmap.ic_launcher)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        return true;
                    }
                });
    }
    public boolean hasSubMenu() {
        return true;
    }
}
