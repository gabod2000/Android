package com.michael.gabod2000;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class GraphsScreen extends AppCompatActivity {
    MaterialSpinner spinner;
    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private LineGraphSeries<DataPoint> series1;

    private int lastX = 0;
    GraphView graph, graphHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs_screen);

        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("Device 1", "Device 2", "Device 3", "Device 4", "Device 5");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Toast.makeText(GraphsScreen.this, "Selected: " + item, Toast.LENGTH_LONG).show();
            }
        });

        LineChartView lineChartView = findViewById(R.id.chart);
        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(3, 9));
        values.add(new PointValue(5, 1));
        values.add(new PointValue(6, 10));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        lineChartView.setLineChartData(data);


        graph = (GraphView) findViewById(R.id.graph);
        graphHumidity = (GraphView) findViewById(R.id.graph_humidity);


        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        Viewport viewport1 = graphHumidity.getViewport();

        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(10);
        viewport.setScrollable(true);
        viewport1.setYAxisBoundsManual(true);
        viewport1.setMinY(0);
        viewport1.setMaxY(10);
        viewport1.setScrollable(true);

        series1 = new LineGraphSeries<DataPoint>();
        graphHumidity.addSeries(series1);


        for (int i = 0; i < values.size(); i++) {
            series.appendData(new DataPoint(values.get(i).getX(), values.get(i).getY()), true, 10);

        }

        List<PointValue> valuesHumidity = new ArrayList<PointValue>();
        valuesHumidity.add(new PointValue(0, 2));
        valuesHumidity.add(new PointValue(3, 1));
        valuesHumidity.add(new PointValue(6, 3));
        valuesHumidity.add(new PointValue(7, 10));

        for (int i = 0; i < valuesHumidity.size(); i++) {
            series1.appendData(new DataPoint(valuesHumidity.get(i).getX(), valuesHumidity.get(i).getY()), true, 10);

        }
    }


}
