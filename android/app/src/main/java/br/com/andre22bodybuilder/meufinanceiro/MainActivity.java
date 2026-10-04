package br.com.andre22bodybuilder.meufinanceiro;
import android.app.*;import android.os.*;import android.webkit.*;import android.graphics.Color;
public class MainActivity extends Activity{
 WebView w;
 @Override public void onCreate(Bundle b){super.onCreate(b); w=new WebView(this); w.setBackgroundColor(Color.rgb(5,7,6)); WebSettings s=w.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setCacheMode(WebSettings.LOAD_DEFAULT); w.setWebViewClient(new WebViewClient()); setContentView(w); w.loadUrl("https://andresalles274-creator.github.io/meu-financeiro/app/");}
 @Override public void onBackPressed(){if(w.canGoBack())w.goBack();else super.onBackPressed();}
}
