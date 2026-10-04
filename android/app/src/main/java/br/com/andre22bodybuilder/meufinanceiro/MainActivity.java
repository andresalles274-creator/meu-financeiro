package br.com.andre22bodybuilder.meufinanceiro;
import android.app.*;import android.os.*;import android.webkit.*;import android.graphics.Color;import android.view.*;import android.content.*;import android.appwidget.*;import android.content.ComponentName;
public class MainActivity extends Activity{
 WebView w;
 @Override public void onCreate(Bundle b){super.onCreate(b);w=new WebView(this);w.setBackgroundColor(Color.rgb(5,7,6));WebSettings s=w.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setCacheMode(WebSettings.LOAD_NO_CACHE);w.addJavascriptInterface(new WidgetBridge(),"AndroidWidget");w.setWebViewClient(new WebViewClient());setContentView(w);if(Build.VERSION.SDK_INT>=21){w.setPadding(0,getStatusBarHeight(),0,0);w.setClipToPadding(false);}w.loadUrl("https://andresalles274-creator.github.io/meu-financeiro/app/?v=21");}
 public class WidgetBridge{
  @JavascriptInterface public void update(String balance,String inToday,String outToday,String recent,String r1,String r2,String r3,String r4,String r5){
   getSharedPreferences("widget",0).edit().putString("balance",balance).putString("in",inToday).putString("out",outToday).putString("recent",recent).putString("r1",r1).putString("r2",r2).putString("r3",r3).putString("r4",r4).putString("r5",r5).putString("updated","Atualizado agora").apply();
   runOnUiThread(()->{AppWidgetManager am=AppWidgetManager.getInstance(MainActivity.this);int[] ids=am.getAppWidgetIds(new ComponentName(MainActivity.this,FinanceWidget.class));new FinanceWidget().onUpdate(MainActivity.this,am,ids);});
  }
 }
 private int getStatusBarHeight(){int id=getResources().getIdentifier("status_bar_height","dimen","android");return id>0?getResources().getDimensionPixelSize(id):0;}
 @Override public void onBackPressed(){if(w.canGoBack())w.goBack();else super.onBackPressed();}
}