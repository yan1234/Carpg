package com.carpg.util;

import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;

import com.carpg.dao.StatisticDao;
import com.carpg.impl.StatisticImpl;

//��Ҫ����Ϊjfreechart��һ����������д���
public class Chart {
	
	//������̬�ַ�������ά�����޸�
	public static String BRAND_YEAR_COUNT = "brand_year_count"; //Ʒ�ư����ͳ��
	public static String BRAND_COUNT_YEAR = "brand_count_year"; //ĳһ���ڸ���Ʒ�Ƶ�״��ͳ��
	public static String BRAND_CARTYPE_COUNT = "brand_cartype_count"; //ĳһ��Ʒ���и������͵�״��ͳ��
	public static String PROBLEM_YEAR_COUNT = "problem_year_count"; //���ⰴ���ͳ��
	public static String PROBLEM_COUNT_YEAR = "problem_count_year"; //ĳһ���ڸ��������״��ͳ��
	public static String PROBLEM_CARTYPE_COUNT = "problem_cartype_count"; //ĳһ�������и������͵�״��ͳ��
	
	private StatisticDao statistic = new StatisticImpl();
	private JFreeChart chart;
	
	
	
	//����chartͼ��������������
	//typeΪʶ�𴴽�ͼ�������,��Ҫ������ͼ����״ͼ����״ͼ��
	//operateΪ���������ͣ���Ҫ��ʾΪͳ��չʾ����Ϣ���״��������ͳ��Ʒ����ʱ��Ϊ���״����������ͼ��
	//paramΪǰ�˴��ݵĲ�������Ҫ�����ں�̨��ȡ��Ӧ������
	public void createChart(String type, String operate, String param){
		//��ʾ��Ҫչʾ��״ͼ
		if (type.equals("pei_chart")){
			//������״ͼ
			createPeiChart(operate, param);
		}//��ʾ��Ҫչʾ����ͼ
		else if (type.equals("line_chart")){
			//��������ͼ
			createXYChart(operate, param);
		}
	}
	//������״ͼ,������������
	//operate��Ҫ��ʾΪͳ��չʾ����Ϣ���״��
	//param��Ҫ�����ڻ�ȡ��Ӧ������
	private void createPeiChart(String operate, String param){
		//����������ʽ����Ҫ���������������
		ChartFactory.setChartTheme(createTheme());
		//����operate���ͽ�����Ӧ�Ļ�ȡ
		//��ʾĳһ���ڸ���Ʒ�Ƶ�����״����
		if (operate.equals(Chart.BRAND_COUNT_YEAR)){
			//Map<String, Integer> map = statistic.getCountByYear_brand(param);
			//����ģ��
			LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
			map.put("2014", 7000);
			map.put("2013", 2000);
			map.put("2012", 1000);
			map.put("2011", 3000);
			map.put("2010", 3500);
			map.put("2009", 2500);
			chart = ChartFactory.createPieChart3D("����Ʒ�Ƶ�״��", 
					createPeiData(map), true, true, false);
			chart.addSubtitle(new TextTitle("��ݣ�"+param));
		}
		//��ʾ��Ʒ���¸������͵�״��ͳ��
		else if (operate.equals(Chart.BRAND_CARTYPE_COUNT)){
			Map<String, Integer> map = statistic.getCountByBrand_carType(param);
			chart = ChartFactory.createPieChart3D("�������͵�״��", 
					createPeiData(map), true, true, false);
			chart.addSubtitle(new TextTitle("Ʒ�ƣ�"+param));
		}
		//��ʾһ���ڸ��������״��ͳ��
		else if (operate.equals(Chart.PROBLEM_COUNT_YEAR)){
			Map<String, Integer> map = statistic.getCountByProblem_year(param);
			chart = ChartFactory.createPieChart3D("���������״��", 
					createPeiData(map), true, true, false);
			chart.addSubtitle(new TextTitle("��ݣ�"+param));
		}
		//��ʾĳһ�������¸�������״����ͳ��
		else if (operate.equals(Chart.PROBLEM_CARTYPE_COUNT)){
			Map<String, Integer> map = statistic.getCountByProblem_carTypes(param);
			chart = ChartFactory.createPieChart3D("�������͵�״��", 
					createPeiData(map), true, true, false);
			chart.addSubtitle(new TextTitle("���⣺"+param));
		}
		//���ñ�״ͼ����ʽ
		setPeiTheme();
	}
	//��������ͼ,������������
	//operate��Ҫ��ʾΪͳ��չʾ����Ϣ���״��
	//param��Ҫ�����ڻ�ȡ��Ӧ������
	private void createXYChart(String operate, String param){
		//����������ʽ����Ҫ���������������
		ChartFactory.setChartTheme(createTheme());
		//����operate���ͽ�����Ӧ�Ļ�ȡ
		
		//��ʾƷ�ư����ͳ��
		if (operate.equals(Chart.BRAND_YEAR_COUNT)){
			//���ݴ��ݵ�Ʒ�Ʋ����ÿ���������
			//Map<String, Integer> map = statistic.getCountByYear_brand(param);
			//����ģ��
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("2014", 7000);
			map.put("2013", 2000);
			map.put("2012", 1000);
			map.put("2011", 3000);
			map.put("2010", 3500);
			map.put("2009", 2500);
			//������Ӧ������ͼ
			chart = ChartFactory.createTimeSeriesChart(
	                "����Ʒ������ͳ��", "���", "�²���",
	                createXYData(map), true, true, true);
			
		}//��ʾ���ⰴ���ͳ��
		else if (operate.equals(Chart.PROBLEM_YEAR_COUNT)){
			Map<String, Integer> map = statistic.getCountByYear_problem(param);
			//������Ӧ������ͼ
			chart = ChartFactory.createTimeSeriesChart(
	                "��������״��ͳ��", "���", "�²���",
	                createXYData(map), true, true, true);
		}
		//������ͼ������ʽ
		setXYTheme();
	}
	//��������ͼ����Դ,����Ϊ��̨��ȡ��Map����
	private XYDataset createXYData(Map<String, Integer> map){
		TimeSeries timeseries = new TimeSeries("����״������",
                org.jfree.data.time.Year.class);
        
        //�õ���ǰ�����
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        //չʾ����ȥ5�������״��
        for (int k=currentYear; k>= currentYear-4; k--){
        	//��ʱȷ����λΪ��������
        	double value = map.get(String.valueOf(k)) / 100;
        	timeseries.add(new Year(k), value);
        }
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        //timeseriescollection.addSeries(timeseries1);
        
        return timeseriescollection;
	}
	//������״ͼ����Դ������Ϊ��ȡ�ĺ�̨��������
	private DefaultPieDataset createPeiData(Map<String, Integer> map){
		//���ñ�ͼ���ݼ�  
		DefaultPieDataset dataset = new DefaultPieDataset();
		//�õ�ͳ����
		LinkedHashMap<String, Integer> linkMap = (LinkedHashMap<String, Integer>)map;
		Set set = linkMap.entrySet();
		Iterator iterator = set.iterator();
		//����hashMap�����뵽����Դ
		while (iterator.hasNext()){
			 Map.Entry element = (Map.Entry)iterator.next(); 
	         String  key = (String)element.getKey(); 
	         int  value = (Integer)element.getValue();
	         dataset.setValue(key, value);
		}
		
		return dataset;
	}
	

	
	//����������ʽ����Ҫ�ǽ��������������
	public StandardChartTheme createTheme(){
		//����������ʽ  
		 StandardChartTheme standardChartTheme=new StandardChartTheme("CN");
		//���ñ�������  
		 standardChartTheme.setExtraLargeFont(new Font("����",Font.BOLD,20));  
		//����ͼ��������  
		 standardChartTheme.setRegularFont(new Font("����",Font.PLAIN,15));  
		//�������������  
		 standardChartTheme.setLargeFont(new Font("����",Font.PLAIN,15));
		 
		 return standardChartTheme;
	}
	
	//��������ͼ��ʽ
	public void setXYTheme(){		
		chart.setBackgroundPaint(Color.white);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(true);
        org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot
                .getRenderer();
        if (xyitemrenderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyitemrenderer;
            xylineandshaperenderer.setBaseShapesVisible(true);
            xylineandshaperenderer.setBaseShapesFilled(true);
         // ��ʾ�ڵ��ֵ  
            xylineandshaperenderer.setBaseItemLabelsVisible(true);  
            xylineandshaperenderer  
                    .setBasePositiveItemLabelPosition(new ItemLabelPosition(  
                            ItemLabelAnchor.OUTSIDE7, TextAnchor.BASELINE_CENTER));  
            xylineandshaperenderer  
                    .setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        }
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("����yyyy����"));
	}
	//���ñ�״ͼ��ʽ
	public void setPeiTheme(){
		PiePlot pieplot = (PiePlot) chart.getPlot();  
		pieplot.setLabelFont(new Font("����", 0, 11));  
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1},{2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());  
		pieplot.setLabelGenerator(standarPieIG);  
		  
		//û�����ݵ�ʱ����ʾ������  
		pieplot.setNoDataMessage("��������ʾ");  
		pieplot.setLabelGap(0.02D);
		
		//��һ��������key,�ڶ���������ͻ����ʾ�Ĵ�С�������Լ�����һ�¿���Ч���������ˣ�   
		//pieplot.setExplodePercent("�ǹ�ǿ��",0.23);
		  
		PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot();  
		//���ÿ�ʼ�Ƕ�  
		pieplot3d.setStartAngle(120D);  
		//���÷���Ϊ��˳ʱ�뷽��  
		pieplot3d.setDirection(Rotation.CLOCKWISE);  
		//����͸���ȣ�0.5FΪ��͸����1Ϊ��͸����0Ϊȫ͸��  
		pieplot3d.setForegroundAlpha(0.7F); 
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
}
