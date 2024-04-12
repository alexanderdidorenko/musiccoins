package com.example.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.base.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.whatnew);
        int dialogWidth = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        Button closeDialog = dialog.findViewById(R.id.button);
        closeDialog.setOnClickListener(view -> dialog.dismiss());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        dialog.getWindow().setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        replaceFragment(new MusicFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setSelectedItemId(R.id.main);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.playlist) {
                replaceFragment(new MessangerFragment());
            }
            else if (itemId == R.id.main) {
                replaceFragment(new MusicFragment());
            }
            else if (itemId == R.id.sets) {
                replaceFragment(new coming_soon());
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linear_layout, fragment);
        fragmentTransaction.commit();
    }
}