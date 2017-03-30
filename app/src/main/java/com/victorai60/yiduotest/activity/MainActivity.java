package com.victorai60.yiduotest.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.victorai60.yiduotest.R;
import com.victorai60.yiduotest.util.HTTPUtil;
import com.victorai60.yiduotest.view.MarkImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private MarkImageView mMarkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplication()));

        initView();

        loadData();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mMarkImageView = (MarkImageView) findViewById(R.id.mark);
        WindowManager windowManager = getWindowManager();
        ViewGroup.LayoutParams layoutParams = mMarkImageView.getLayoutParams();
        layoutParams.height = windowManager.getDefaultDisplay().getWidth() * 3 / 4;
        mMarkImageView.setLayoutParams(layoutParams);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        new AsyncTask<String, Integer, String>() {

            @Override
            protected String doInBackground(String... params) {
                return HTTPUtil.get(params[0]);
            }

            @Override
            protected void onPostExecute(String result) {
                if (result == null) {
                    Toast.makeText(MainActivity.this, "获取JSON数据失败", Toast.LENGTH_SHORT).show();

                    return;
                }

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    jsonObject = jsonObject.getJSONObject("image_test");
                    ImageLoader.getInstance().displayImage(jsonObject.getString("image_url"), mMarkImageView);
                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(MainActivity.this, "解析JSON数据失败", Toast.LENGTH_SHORT).show();
                }
            }

        }.execute("http://f1.eduo.wang/mark.html");
    }

}
