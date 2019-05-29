/**
 * 
 */
package com.saineng.campussystem.hcrfu;


/**
 * @author
 *
 */
public class Reader {

	public static final int ERR_NO_INI_FILE			=	-0x01;
	public static final int ERR_COMMUNICATION		=	-0x10;
	public static final int ERR_TIMEOUT				=   -0x11;
	public static final int ERR_COMM				=   -0x12;
	public static final int ERR_TURNHEX             =                -0x13;
	public static final int ERR_TURNASC             =                -0x14;
	public static final int ERR_OPENCOMM			=	-0x20;
	public static final int ERR_GETCOMMSTATE		=	-0x21;
	public static final int ERR_SETCOMMSTATE		=	-0x22;
	public static final int ERR_COMM_STILL_OPEN	    =		-0x23;
	public static final int ERR_EXIST               =		-0x24;
	public static final int ERR_FORMAT				=   -0x30;
	public static final int ERR_DATAFORMAT			=	-0x31;
	public static final int ERR_LENGTH				=-0x32;
	public static final int ERR_READ				=-0x40;
	public static final int ERR_WRITE				=-0x41;
	public static final int ERR_NOT_RECEIVE			=	-0x42;
	public static final int ERR_LESS				=-0x50;
	public static final int ERR_OVER                =-0x51;
	public static final int ERR_COMPARE             =-0x52;
	public static final int ERR_TURN                =-0x53;
	public static final int ERR_TRANSMODE           =-0x54;

	/**
	 *
	 */
	public Reader() {
		// TODO Auto-generated constructor stub
	}

	static {
		System.loadLibrary("HC_RF_V100");// 调用dll
	}

	public long hc_init(int port, long paras)
	{
		long icdev = -1;
		//TODO
		return icdev;
	}
	public int hc_exit(long icdev)
	{
		return rf_exit(icdev);
	}
	/**
	 * @param port
	 * @param paras
	 * @return
	 */
	private native int rf_init(int port, long paras);
	/** 连接设备
	 * @param icdev
	 * @return
	 */
	private native int rf_exit(long icdev);
	/** 断开设备
	 * @param icdev
	 * @param mode
	 * @return
	 */
	public native int rf_request(long icdev , int mode);
	/** 寻卡
	 * @param icdev
	 * @param mode
	 * @return
	 */
	public native int rf_request_std(long icdev , int mode);
	/** 标准 寻卡
	 * @param icdev
	 * @param bcnt
	 * @return
	 */
	public native int rf_anticoll(long icdev, int bcnt);
	/** 防冲突
	 * @param icdev
	 * @param snr
	 * @return
	 */
	public native int rf_select(long icdev, int snr );
	/** 选卡
	 * @param icdev
	 * @param mode
	 * @param secSr
	 * @return
	 */
	public native int rf_authentication(long icdev, int mode, int secSr );
	/** 认证（下载密码方式，执行此命令需先装载密码）
	 * @param icdev
	 * @param mode
	 * @param secSr
	 * @param key
	 * @return
	 */
	public native int rf_authentication_key(long icdev, int mode, int secSr, String key);
	/** 认证（下载密码方式，执行此命令需先装载密码，可以操作大于16扇区）
	 * @param icdev
	 * @return
	 */
	public native int rf_halt(long icdev );
	/** 停卡
	 * @param icdev
	 * @param adr
	 * @return
	 */
	public native int rf_read(long icdev, int adr );
	/** 读数据，返回16字节数据
	 * @param icdev
	 * @param adr
	 * @param data
	 * @return
	 */
	public native int rf_write(long icdev, int adr, String data );
	/** 写数据
	 * @param icdev
	 * @param mode
	 * @param secSr
	 * @param key
	 * @return
	 */
	public native int rf_load_key(long icdev, int mode, int secSr, String key );
	/** 装载密码
	 * @param icdev
	 * @param adr
	 * @param value
	 * @return
	 */
	public native int rf_increment(long icdev, int adr, int value );
	/** 加块值
	 * @param icdev
	 * @param adr
	 * @param value
	 * @return
	 */
	public native int rf_decrement(long icdev, int adr, int value );
	/** 减块值
	 * @param icdev
	 * @return
	 */
	public native int rf_restore(long icdev );
	/** 回传函数，将EEPROM中的内容传入卡的内部寄存器
	 * @param icdev
	 * @return
	 */
	public native int rf_transfer(long icdev );
	/**  将寄存器的内容传送到EEPROM中
	 * @param icdev
	 * @param mode
	 * @return
	 */
	public native int rf_card(long icdev, int mode );
	/** 高级寻卡，包含寻卡、防冲突、选卡 3个操作
	 * @param icdev
	 * @param adr
	 * @param value
	 * @return
	 */
	public native int rf_initval(long icdev, int adr, int value);
	/** 初始化块值
	 * @param icdev
	 * @param adr
	 * @return
	 */
	public native int rf_readval(long icdev, int adr);
	/** 读块值
	 * @param icdev
	 * @param msec
	 * @return
	 */
	public native int rf_reset(long icdev, int msec);
	/** 射频读写模块复位
	 * @param icdev
	 * @param msec
	 * @return
	 */
	public native int rf_beep(long icdev, int msec);
	/** 蜂鸣
	 * @param icdev
	 * @return
	 */
	public native int rf_getHardwardVer(long icdev);
	/** 读硬件版本号
	 * @param icdev
	 * @return
	 */
	public native int rf_pro_rst(long icdev );
	/**
	 * @param icdev
	 * @param problock
	 * @return
	 */
	public native int rf_pro_trn(long icdev, String problock);
	/**
	 * @param icdev
	 * @return
	 */
	public native int rf_pro_halt(long icdev);

	/**
	 * @return
	 */
	public String getStrAtr() {
		return strAtr;
	}

	/**
	 * @return
	 */
	public String getStrApduResult() {
		return strApduResult;
	}

	/**
	 *
	 * @return 返回固件版本号.调用前请确认rf_getHardwardVer()接口调用成功。
	 */
	public String getStrHardwardVer() {
		return strHardwardVer;
	}

	public int getTagType() {
		return tagType;
	}
	public int getSnr() {
		return snr;
	}
	public int getSize() {
		return size;
	}
	public int getValue() {
		return value;
	}
	public String getCardData() {
		return cardData;
	}

	private int tagType;
	private int snr;
	private int size;
	private int value;
	private String cardData;
	private String strHardwardVer;
	private String strAtr = "";
	private String strApduResult = "";

	protected long devHandle;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
