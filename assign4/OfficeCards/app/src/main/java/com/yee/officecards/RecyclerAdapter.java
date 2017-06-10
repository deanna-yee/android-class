package com.yee.officecards;

/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: Employee.java
* File Description: contains the class definition for RecyclerAdapter and stores the data for employees
* Assignment #: 4
* Date: 3/27/17
*/

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    //stores all the employees
    private ArrayList<Employee> employees;

    public RecyclerAdapter(){

        //adds all the employees to the array list
        employees = new ArrayList<Employee>();
        loadData();
    }

    public void loadData(){
        //stores all the names of the employees
        String[] names = {"Pamela Beesly",
                "Andy Bernard",
                "Creed Bratton",
                "Jim Halpert",
                "Ryan Howard",
                "Stanley Hudson",
                "Kelly Kapoor",
                "Phyllis Lapin",
                "Kevin Malone",
                "Angela Martin",
                "Oscar Martinez",
                "Meredith Palmer",
                "Dwight Schrute",
                "Michael Scott",
                "Toby Flenderson"
        };

        //stores all the employee titles
        String[] titles = { "Receptionist",
                "Sales Director",
                "Quality Assurance",
                "Assistant Regional Manager",
                "Vice President, North East",
                "Sales Representative",
                "Customer Service Rep.",
                "Sales Representative",
                "Accountant",
                "Senior Accountant",
                "Accountant",
                "Supplier Relations",
                "Assistant Regional Manager",
                "Regional Manager",
                "Human Resources Manager"
        };

        //stores all the images of the employees
        int[] employeeImages = {R.drawable.pamela_beesly,
                R.drawable.andy_bernard,
                R.drawable.creed_bratton,
                R.drawable.jim_halpert,
                R.drawable.ryan_howard,
                R.drawable.stanley_hudson,
                R.drawable.kelly_kapoor,
                R.drawable.phyllis_lapin,
                R.drawable.kevin_malone,
                R.drawable.angela_martin,
                R.drawable.oscar_martinez,
                R.drawable.meredith_palmer,
                R.drawable.dwight_schrute,
                R.drawable.michael_scott,
                R.drawable.toby_flenderson
        };

        //adds employees to the arraylist
        for(int i = 0; i < names.length; i++){
            Employee employee = new Employee(names[i], titles[i], employeeImages[i]);
            employees.add(employee);
        }

    }

    //creates a view holder of the card
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    //sets the name, title, and image of employee to a card
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        viewHolder.employeeName.setText(employees.get(i).getName());
        viewHolder.employeeTitle.setText(employees.get(i).getPosition());
        viewHolder.employeeImage.setImageResource(employees.get(i).getImage());
    }

    //gets the number of employees
    @Override
    public int getItemCount(){
        return employees.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView employeeImage;
        public TextView employeeName;
        public TextView employeeTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            //get image and text views for card layouts
            employeeImage = (ImageView)itemView.findViewById(R.id.employee_image);
            employeeName = (TextView)itemView.findViewById(R.id.employee_name);
            employeeTitle = (TextView)itemView.findViewById(R.id.employee_title);

            //detects when a card is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on " +
                            employees.get(position).getName(),
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }
    }
}
