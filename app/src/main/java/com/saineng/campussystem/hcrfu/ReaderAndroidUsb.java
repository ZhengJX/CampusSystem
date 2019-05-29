package com.saineng.campussystem.hcrfu;


import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;

public class ReaderAndroidUsb extends Reader {

	private UsbRequest urReceive = new UsbRequest();
	private UsbDevice m_hidReader;
	private UsbDeviceConnection m_hidCom; // 通讯连接
	private UsbManager m_manager;
	private int m_timeouts = 5600;


	private int protocolType = 0; // 协议类型 ,设备采用的通讯协议，

	private static final String INTERFACE_USB = "AndroidUSB";

	// 控制传输常量
	public static int HID_REPORT_SIZE_254 = 254; // 报告长度

	private final int HID_GETREPORT_TYPE = UsbConstants.USB_TYPE_CLASS
			| UsbConstants.USB_INTERFACE_SUBCLASS_BOOT
			| UsbConstants.USB_DIR_IN;
	private final int HID_SETREPORT_TYPE = UsbConstants.USB_TYPE_CLASS
			| UsbConstants.USB_INTERFACE_SUBCLASS_BOOT
			| UsbConstants.USB_DIR_OUT;
	private final int HID_GETREPORT_REQUEST = 0X01;
	private final int HID_SETREPORT_REQUEST = 0X09;
	private final int HID_GETREPORT_VALUE = 0X0306; // 03:feature 06:report id
	private final int HID_SETREPORT_VALUE = 0X0306;

	/**
	 * 构造读卡器的实例
	 *
	 * @param manager
	 *            (UsbManager) getSystemService(Context.USB_SERVICE)返沪的实例
	 */
	public ReaderAndroidUsb(android.hardware.usb.UsbManager manager) {
		m_manager = manager;
	}

	public static boolean isSupported(android.hardware.usb.UsbDevice device) {
		boolean bSt = false;
		int iVID = device.getVendorId();
		int iPID = device.getProductId();
		if ((iVID == 0x10c4 && iPID == 0x1414)) {
			bSt = true;
		}
		return bSt;
	}

	public long hc_init(android.hardware.usb.UsbDevice device)
			throws Exception {
		long st = -1;
		if (m_manager == null) {
			return ERR_COMM;
		}
		if (m_hidCom != null) {
			m_hidCom.close();
		}
		m_hidReader = device;
		int count = device.getInterfaceCount();
		m_hidCom = m_manager.openDevice(device);
		if (m_hidCom != null) {
			devHandle = m_hidCom.getFileDescriptor();
			st = 0;	//句柄值为0表示USB设备
		}
		return st;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mwcard.Reader#closeReader()
	 */
	@Override
	public int hc_exit(long icdev) {
		// TODO Auto-generated method stub
		if (m_hidCom != null) {
			m_hidCom.close();
		}
		devHandle = -1;
		return 0;
	}

	public int claim() {
		int st = 0;
		boolean status = m_hidCom.claimInterface(m_hidReader.getInterface(0),
				true);
		if (status) {
			st = 1;
			return 1;
		}
		return st;
	}

	public int release() {
		int st = 0;
		boolean status = m_hidCom.releaseInterface(m_hidReader.getInterface(0));
		if (status) {
			st = 1;
			return 1;
		}
		return st;
	}

	public int writeData(byte[] src, int timeOuts) {

		int flag = 0;
		int len = 0;
		boolean status = false;

		// 1.发送数据
		int iTotal = src.length;
		int index = 0;
		do {
			byte[] bArraySend = new byte[255];
			bArraySend[0] = 0x06; // reprot id
			if (iTotal <= HID_REPORT_SIZE_254) {
				System.arraycopy(src, index, bArraySend, 1, iTotal);
				iTotal = 0;
			} else {
				System.arraycopy(src, index, bArraySend, 1, HID_REPORT_SIZE_254);
				iTotal -= HID_REPORT_SIZE_254;
				index += HID_REPORT_SIZE_254;
			}
			len = writeFeature(bArraySend, timeOuts);
			if (len <= 0) {
				// m_hidCom.releaseInterface(m_hidReader.getInterface(0));
				return ERR_COMMUNICATION;
			}
		} while (iTotal != 0);
		return flag;
	}

	public byte[] readData(int uiRead, int timeOuts) {
		int flag = 0;
		int len = 0;

		byte[] bArrayResult = null;
		byte[] bArrayReceive;


		bArrayReceive = new byte[HID_REPORT_SIZE_254 + 1];
		bArrayReceive[0] = 0x06; // reprot id
		int t = 100;
		while(true)
		{
			len = m_hidCom.controlTransfer(HID_GETREPORT_TYPE,
					HID_GETREPORT_REQUEST, HID_GETREPORT_VALUE, 0, bArrayReceive,
					bArrayReceive.length, timeOuts);
			if (len < 0) {
				// m_hidCom.releaseInterface(m_hidReader.getInterface(0));
				if(t-- <= 0)
				{
					return null;
				}
				try{
					Thread.sleep(10);
				}catch(Exception e){}
				finally{

				}
			}
			else{
				break;
			}
		}

		bArrayResult = new byte[HID_REPORT_SIZE_254];
		System.arraycopy(bArrayReceive, 1, bArrayResult, 0, HID_REPORT_SIZE_254);

		// m_hidCom.releaseInterface(m_hidReader.getInterface(0));
		return bArrayResult;
	}

	private int writeFeature(byte[] packet, int timeout) {
		int len = m_hidCom.controlTransfer(HID_SETREPORT_TYPE,
				HID_SETREPORT_REQUEST, HID_SETREPORT_VALUE, 0, packet,
				packet.length, timeout);
		return len;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
