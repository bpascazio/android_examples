package org.android.webservice.client.samples;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//
// NOTE: Webservice is here:http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?op=CurrencyName
//       Also look here for ISO codes... http://www.oanda.com/help/currency-iso-code-country
//

public class AndroidClientServiceActivity extends Activity {

	private static final String SOAP_ACTION = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";

	private static final String OPERATION_NAME = "CurrencyName";

	private static final String WSDL_TARGET_NAMESPACE = "http://www.oorsprong.org/websamples.countryinfo";

	private static final String SOAP_ADDRESS = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(this);

		setContentView(textView);

		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
				OPERATION_NAME);
		
		request.addProperty("sCurrencyISOCode", "CNY");

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
	//	envelope.dotNet = true;

		envelope.setOutputSoapObject(request);

		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

		try

		{

			httpTransport.call(SOAP_ACTION, envelope);

			Log.d("SOAP",envelope.toString());
			Object response = envelope.getResponse();

			textView.setText(response.toString());

		}

		catch (Exception exception)

		{

			textView.setText(exception.toString());

		}

	}
}