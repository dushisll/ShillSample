package sample.shillsample.ui.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import sample.shillsample.R;
import sample.shillsample.databinding.ActivityDatabindingBinding;

/**
 * Created by we on 2016/12/18.
 */

public class DataBindingActivity extends AppCompatActivity{

    ActivityDatabindingBinding binding;
    Employee employee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_databinding);
        employee = new Employee();
        binding =
                DataBindingUtil.setContentView(DataBindingActivity.this, R.layout.activity_databinding);
        binding.setEmployee(employee);
        binding.setPresenter(new Presenter());

    }

    public class Presenter{
        public void onTextChanged(CharSequence s,int start,int before,int count){
            employee.firstname = s.toString();
            binding.setEmployee(employee);
        }


        public void onClick(View view){
            Toast.makeText(DataBindingActivity.this,"click",Toast.LENGTH_SHORT).show();
        }

        public void onClickListenerBinding(Employee employee){
            Toast.makeText(DataBindingActivity.this,"click"+employee.firstname,Toast.LENGTH_SHORT).show();
        }
    }
}
