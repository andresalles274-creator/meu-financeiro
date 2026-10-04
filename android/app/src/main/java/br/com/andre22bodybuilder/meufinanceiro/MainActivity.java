package br.com.andre22bodybuilder.meufinanceiro;
import android.app.*;import android.os.*;import android.webkit.*;import android.graphics.Color;import android.view.*;
public class MainActivity extends Activity{
 WebView w;
 @Override public void onCreate(Bundle b){super.onCreate(b); if(Build.VERSION.SDK_INT>=35){getWindow().setStatusBarColor(Color.rgb(5,7,6));getWindow().setNavigationBarColor(Color.rgb(5,7,6));getWindow().getDecorView().setSystemUiVisibility(0);} w=new WebView(this); w.setBackgroundColor(Color.rgb(5,7,6)); WebSettings s=w.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setCacheMode(WebSettings.LOAD_DEFAULT); w.setWebViewClient(new WebViewClient()); setContentView(w); w.loadUrl("https://andresalles274-creator.github.io/meu-financeiro/app/");}
 @Override public void onBackPressed(){if(w.canGoBack())w.goBack();else super.onBackPressed();}
}
