package com.saineng.campussystem.activitys;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.saineng.campussystem.R;
import com.saineng.campussystem.activitys.presenter.MainPImpl;
import com.saineng.campussystem.db.DbService;
import com.saineng.campussystem.hcrfu.Reader;
import com.saineng.campussystem.hcrfu.ReaderAndroidUsb;
import com.saineng.campussystem.models.SimpleFileCallBack;
import com.saineng.campussystem.utils.LogUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private MainPImpl mainP;
    private DbService dbService;
    private Button mBtnCard;
    private Button mBtnOrder;

    /******链接设备******/
    public static Reader myReader;// =new ReaderAndroidUsb();
    public static ReaderAndroidUsb readerAndroidUsb; // 安卓usb打开方式跟串口方式不一样
    private UsbDeviceConnection connection = null;
    private UsbManager manager;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private PendingIntent mPermissionIntent;
    public static long fd = -1;	//保存社保句柄

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        public void onReceive(Context paramAnonymousContext,
                              Intent paramAnonymousIntent) {
            if (ACTION_USB_PERMISSION.equals(paramAnonymousIntent.getAction())){
                try {
                    UsbDevice i = ((UsbDevice) paramAnonymousIntent
                            .getParcelableExtra("device"));
                    if (paramAnonymousIntent.getBooleanExtra("permission",
                            false)) {
                        readerAndroidUsb = new ReaderAndroidUsb(manager);
                        try {
                            int st = (int)readerAndroidUsb.hc_init(i);
                            if (st >= 0) {
                                myReader = readerAndroidUsb;

                            }
                        } catch (Exception e) {
                            // TODO: handle exception
                            LogUtils.e("openDevice failed");
                        }
                    } else {

                    }
                } finally {
                }
            }
        }
    };


    public int initUsbDevice() {
        // ContextWrapper mContext = null;
        int st = 1;
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();


        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            Log.e("google",
                    device.getDeviceName() + " "
                            + Integer.toHexString(device.getVendorId()) + " "
                            + Integer.toHexString(device.getProductId()));
            if (!ReaderAndroidUsb.isSupported(device)) {
                continue;
            }

            if (!manager.hasPermission(device)) {
                manager.requestPermission(device, mPermissionIntent);
            } else {
                readerAndroidUsb = new ReaderAndroidUsb(manager);
                try {
                    st = (int)readerAndroidUsb.hc_init(device);
                    if (st >= 0) {
                        myReader = readerAndroidUsb;
                        fd = st;
                        st = 0;
                        break;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    Log.e("google", "openDevice failed");
                }
            }
        }
        return st;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(
                ACTION_USB_PERMISSION), 0);
        manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        registerReceiver(this.mUsbReceiver, filter);

        dbService = DbService.getInstance(this);
        mainP = new MainPImpl();

        LogUtils.e("  dbService.loadAllNote() " + dbService.loadAllNote().size());
    }

    private void usbConnect(){

        long st = 0;
        st = initUsbDevice();
        if (0 == st )
        {
            st = myReader.rf_beep(fd, 20);
            st = myReader.rf_getHardwardVer(fd);
            if (st == 0) {
//                ETversion.setText(myReader.getStrHardwardVer());
//                btn_operate.setEnabled(true);
            } else {
//                Tip.setText("设备连接失败." + String.valueOf(st));
            }
        }
    }

    private void usbDisconnect(){
        try
        {
            //ClsUtils.removeBond(SearchActivity.remoteDevice);
            //myReader.mBTHSocket.close();
            if (fd >= 0)
            {
                myReader.hc_exit(fd);
                fd = -1;
            }
//            ETversion.setText("");
//            Tip.setText("设备关闭成功.");
        }
        catch (Exception ex)
        {
//            Tip.setText(ex.getMessage());
        }
    }


    public void bt_rfcard(View source)/*以下是M1卡操作触发函数*/
    {
//		EditText PUTip=(EditText) findViewById(R.id.lay3_edit_tip); //提示信息框
//		EditText E_CardNo=(EditText) findViewById(R.id.lay3_edit_cardno); //提示信息框
//
		try
		{
			int st = 0;
			st = MainActivity.myReader.rf_reset(MainActivity.fd, 10);


			st = MainActivity.myReader.rf_card(MainActivity.fd, 1);
			if (st == 0)
			{
				int snr = MainActivity.myReader.getSnr();

//				E_CardNo.setText(Integer.toHexString(snr));
//				PUTip.setText("打开卡片成功。");
			}
			else
			{
//				PUTip.setText("打开卡片失败，请把卡放在读卡器上");
			}

			//String snr=MainActivity.myReader.openCard(1);
			//E_CardNo.setText(snr);

		}
		catch (Exception ex)
    	{
//			E_CardNo.setText("");
//			PUTip.setText(ex.getMessage());
    	}

    }


    /**
     * 读取本地文件中JSON字符串
     *
     * @param fileName
     * @return
     */
    /**
     * private String getJson(String fileName)
     * {
     * <p>
     * StringBuilder stringBuilder = new StringBuilder();
     * try
     * {
     * BufferedReader bf = new BufferedReader(new InputStreamReader(
     * getAssets().open(fileName)));
     * String line;
     * while ((line = bf.readLine()) != null)
     * {
     * stringBuilder.append(line);
     * }
     * }
     * catch (IOException e)
     * {
     * e.printStackTrace();
     * }
     * return stringBuilder.toString();
     * }
     */


    private void downFile(String path)
    {
        mainP.downLoadFile(this, path,
                "feng.txt",
                new SimpleFileCallBack<File>()
                {
                    MaterialDialog dialog;

                    @Override
                    public void onStart()
                    {
                        new MaterialDialog.Builder(MainActivity.this)
                                .titleColorRes(R.color.colorAccent)
                                .negativeColorRes(R.color.colorAccent)
                                .title("下载提示")
                                .content("正在下载...")
                                .contentGravity(GravityEnum.CENTER)
                                .progress(false, 100, false)
                                .negativeText("取消")
                                .cancelable(false)
                                .onNegative(new MaterialDialog.SingleButtonCallback()
                                {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog,
                                                        @NonNull DialogAction which)
                                    {
                                        mainP.cancleRequest(MainActivity.this);
                                    }
                                })
                                .showListener(new DialogInterface.OnShowListener()
                                {

                                    @Override
                                    public void onShow(DialogInterface dialogInterface)
                                    {
                                        dialog = (MaterialDialog) dialogInterface;
                                    }
                                }).show();

                    }

                    @Override
                    public void onError(String code, String msg)
                    {
                        dialog.hide();
                    }

                    @Override
                    public void onSuccess(File file)
                    {
                        dialog.hide();
                        LogUtils.e(" file " + file.getPath());
                    }

                    @Override
                    public void inProgress(float progress)
                    {
                        super.inProgress(progress);
                        dialog.setProgress((int) (100 * progress));
                        LogUtils.e(" (int) (100 * progress) " + (int) (100 * progress));
                    }
                });
    }

    private void initView()
    {

        mBtnCard = (Button) findViewById(R.id.btn_card);
        mBtnCard.setOnClickListener(this);
        mBtnOrder = (Button) findViewById(R.id.btn_order);
        mBtnOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
//            case R.id.ll_home:
//
//                LogUtils.e("点击");
//                //http://124.172.114.27/imtt.dd.qq.com/16891/C85B9E20279DA45339D27F496093C303.apk?mkey=59312ed443af5529&f=eea4&c=0&fsname=com.chemm.wcjsth_1.0_1.apk&csr=1bbd&p=.apk
//                downFile("http://192.168.1.119/json.txt");
//                break;
            case R.id.btn_card:

                startActivity(new Intent(this,CreditActivity.class));
                break;
            case R.id.btn_order:

                startActivity(new Intent(this,OrderActivity.class));
                break;
        }
    }
}
