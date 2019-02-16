package com.muhz.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
//import android.widget.ScrollView;
import android.widget.TextView;
//import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private Button btn_clean; //【清空键】
    private Button btn_back; //【退格键】
    private Button btn_equ; //【=键】
    private Button btn_lBar; //【（键】
    private Button btn_rBar; //【）键】
    private Button btn_add; //【+键】
    private Button btn_sub; //【-键】
    private Button btn_div; //【*键】
    private Button btn_mul; //【/键】
    private Button btn_rad;//【√键】
    private Button btn_pow;//【^键】
    private Button btn_log;//【log键】
    private Button btn_e;//【e键】
    private Button btn_zero; //【0键】
    private Button btn_one; //【1键】
    private Button btn_two; //【2键】
    private Button btn_thr; //【3键】
    private Button btn_for; //【4键】
    private Button btn_fiv; //【5键】
    private Button btn_six; //【6键】
    private Button btn_sev; //【7键】
    private Button btn_eig; //【8键】
    private Button btn_nin; //【9键】
    private Button btn_point; //【.键】
    private TextView tv_1; // 表达式显示框
    private TextView tv_2; // 结果显示框
    private String showResult = ""; // 初始化结果字符串
    private Inputor zz; // 输入控制器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_1 = (TextView) findViewById(R.id.tv_1); //获取表达式显示框
        tv_1.setMovementMethod(ScrollingMovementMethod.getInstance());//显示框滑动
        tv_1.setHorizontallyScrolling(true);//水平滑动

        tv_2 = (TextView) findViewById(R.id.tv_2); //获取结果显示框
        tv_2.setMovementMethod(ScrollingMovementMethod.getInstance());//显示框滑动
        tv_2.setHorizontallyScrolling(true);//水平滑动

        zz = new Inputor(tv_1); // 实例化输入控制器

        btn_back = (Button) findViewById(R.id.btn_back); //获取【退格键】

        //【退格键】点击效果
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zz.showExp.length()==0) {
                    // 无操作
                } else {
                    zz.delete();
                }
            }
        });
        btn_clean = (Button) findViewById(R.id.btn_clean); //获取【清空键】

        //【清空键】点击效果
        btn_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.clean();
                showResult = "";
                tv_2.setText(showResult);
            }
        });

        btn_one = (Button) findViewById(R.id.btn_one); //获取【1键】

        //【1键】点击效果
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("1");
            }
        });

        btn_two = (Button) findViewById(R.id.btn_two); //获取【2键】

        //【2键】点击效果
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("2");
            }
        });


        btn_thr = (Button) findViewById(R.id.btn_thr); //获取【3键】

        //【3键】点击效果
        btn_thr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("3");
            }
        });


        btn_for = (Button) findViewById(R.id.btn_for); //获取【4键】

        //【4键】点击效果
        btn_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("4");
            }
        });

        btn_fiv = (Button) findViewById(R.id.btn_fiv); //获取【5键】

        //【5键】点击效果
        btn_fiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("5");
            }
        });

        btn_six = (Button) findViewById(R.id.btn_six); //获取【6键】

        //【6键】点击效果
        btn_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("6");
            }
        });

        btn_sev = (Button) findViewById(R.id.btn_sev); //获取【7键】

        //【7键】点击效果
        btn_sev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("7");
            }
        });

        btn_eig = (Button) findViewById(R.id.btn_eig); //获取【8键】

        //【8键】点击效果
        btn_eig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("8");
            }
        });

        btn_nin = (Button) findViewById(R.id.btn_nin); //获取【9键】

        //【9键】点击效果
        btn_nin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("9");
            }
        });

        btn_zero = (Button) findViewById(R.id.btn_zero); //获取【0键】

        //【0键】点击效果
        btn_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputValue("0");
            }
        });

        btn_e = (Button) findViewById(R.id.btn_e);//获取【e键】

        //【e键】点击效果
        btn_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputE();
            }
        });

        btn_point = (Button) findViewById(R.id.btn_point); //获取【.键】

        //【.键】点击效果
        btn_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zz.showExp.length() == 0) {
                    //当表达式为空时，点击【.键】，输入0.
                    zz.inputValue("0.");
                } else {
                    if (zz.showExp.charAt(zz.showExp.length() - 1) != '.' &&
                            zz.showExp.charAt(zz.showExp.length() - 1) != '(' &&
                                zz.showExp.charAt(zz.showExp.length() - 1) != ')') {
                        //当前一个字符不为. (  )时
                        zz.inputValue(".");
                    } else {
                        //不能输入
                    }
                }
            }
        });

        btn_equ = (Button) findViewById(R.id.btn_equ); //获取【=键】

        //【=键】点击效果
        btn_equ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zz.showExp.length() == 0) {
                    //当表达式为空时，结果也为空
                    showResult = "";
                    tv_2.setText(showResult);
                } else {
                    //当表达式不为空时，计算表达式，并显示结果
                    showResult = zz.getResult();
                    tv_2.setText(showResult);
                }
            }
        });

        btn_add = (Button) findViewById(R.id.btn_add); //获取【+键】

        //【+键】点击效果
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputOp("+");
            }
        });

        btn_sub = (Button) findViewById(R.id.btn_sub); //获取【-键】

        //【-键】点击效果
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputOp("-");
            }
        });

        btn_mul = (Button) findViewById(R.id.btn_mul); //获取【*键】

        //【*键】点击效果
        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputOp("*");
            }
        });

        btn_div = (Button) findViewById(R.id.btn_div); //获取【/键】

        //【/键】点击效果
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputOp("/");
            }
        });

        btn_rad = (Button) findViewById(R.id.btn_rad); //获取【√键】

        //【√键】点击效果
        btn_rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputOp("√");
            }
        });

        btn_pow = (Button) findViewById(R.id.btn_pow); //获取【^键】

        //【^键】点击效果
        btn_pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputOp("^");
            }
        });

        btn_log = (Button) findViewById(R.id.btn_log); //获取【log键】

        //【log键】点击效果
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputOp("log");
            }
        });

        btn_lBar = (Button) findViewById(R.id.btn_lBar); //获取【(键】

        //【(键】点击效果
        btn_lBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputOp("(");
            }
        });

        btn_rBar = (Button) findViewById(R.id.btn_rBar); //获取【)键】

        //【)键】点击效果
        btn_rBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zz.inputOp(")");
            }
        });
    }

    /**public static void scrollToBottom(final ScrollView scroll, final View inner) {
        Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                // 自动滑动
                if (scroll == null || inner == null) {
                    return;
                }
                // 内层高度超过外层
                int offset = inner.getMeasuredWidth() - scroll.getMeasuredWidth();
                if (offset < 0) {
                    offset = 0;
                }
                scroll.scrollTo(offset,0);
            }
        });
    }**/
}

