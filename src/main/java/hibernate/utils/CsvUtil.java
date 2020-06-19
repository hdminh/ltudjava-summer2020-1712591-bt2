package hibernate.utils;

import hibernate.controller.QuanLySinhVienController;
import hibernate.dao.LophocDao;
import hibernate.entity.BangdiemEntity;
import hibernate.entity.LophocEntity;
import hibernate.entity.MonhocEntity;
import hibernate.entity.SinhvienEntity;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    static QuanLySinhVienController quanLySinhVienController = new QuanLySinhVienController();
    private static JFileChooser fc = new JFileChooser();

//    public void ExportCsv(List<SinhvienEntity> studentList){
//        FileWriter fw = null;
//        fc.setDialogTitle("Chọn đường dẫn để Export");
//        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        File file = null;
//        int result = fc.showSaveDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION){
//            file = fc.getSelectedFile();
//        }
//        try {
//            assert file != null;
//            fw = new FileWriter(file.getAbsolutePath() + "\\Export.csv");
//            fw.append("Ma HS, Ten HS, Diem TB, Hinh, Dia chi, Ghi chu\n");
//            for (SinhvienEntity student : studentList){
//                fw.append(String.valueOf(student.getMssv()));
//                fw.append(",");
//                fw.append(student.getTenHS());
//                fw.append(",");
//                fw.append(String.valueOf(student.getDiem()));
//                fw.append(",");
//                fw.append(student.getHinh());
//                fw.append(",");
//                fw.append(student.getDiaChi());
//                fw.append(",");
//                fw.append(student.getGhiChu());
//                fw.append("\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (fw != null) {
//                    fw.flush();
//                    fw.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static List<SinhvienEntity> ImportCsv(){
        BufferedReader br;
        List<SinhvienEntity> students = new ArrayList<SinhvienEntity>();
        String line;
        FileReader fr;
        fc.setDialogTitle("Chọn file để Import");
        File file = null;
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
        }
        if (file == null)
            return null;
        try {
            assert file != null;
            fr = new FileReader(file.getAbsolutePath());
            br = new BufferedReader(fr);
            String lop = br.readLine();
            LophocDao lophocDao = new LophocDao();
            List<LophocEntity> lophoc = lophocDao.readListLopHoc();
            LophocEntity lophocEntity = new LophocEntity(lop);
            if (!lophoc.contains(lophocEntity))
                lophocDao.add(lophocEntity);
            while ((line = br.readLine()) != null){
                String[] list = line.split(",");
                if (list.length > 0){
                    SinhvienEntity student = new SinhvienEntity();
                    student.setMssv(list[0]);
                    student.setHoten(list[1]);
                    student.setGioitinh(list[2]);
                    student.setCmnd(list[3]);
                    student.setLop(lophocEntity);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<MonhocEntity> ImportMonhoc(){
        BufferedReader br;
        List<MonhocEntity> monhocList = new ArrayList<MonhocEntity>();
        String line;
        FileReader fr;
        fc.setDialogTitle("Chọn file để Import");
        File file = null;
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
        }
        if (file == null){
            return null;
        }
        try {
            fr = new FileReader(file.getAbsolutePath());
            br = new BufferedReader(fr);
            String lop = br.readLine();
            LophocDao lophocDao = new LophocDao();
            List<LophocEntity> lophoc = lophocDao.readListLopHoc();
            LophocEntity lophocEntity = new LophocEntity(lop);
            if (!lophoc.contains(lophocEntity))
                lophocDao.add(lophocEntity);
            while ((line = br.readLine()) != null){
                String[] list = line.split(",");
                if (list.length > 0){
                    MonhocEntity monhoc = new MonhocEntity();
                    monhoc.setMamon(list[0]);
                    monhoc.setTenmonhoc(list[1]);
                    monhoc.setPhonghoc(list[2]);
                    monhoc.setLophoc(lophocEntity);
                    monhocList.add(monhoc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return monhocList;
    }

    public static List<BangdiemEntity> ImportBangdiem(){
        BufferedReader br;
        List<BangdiemEntity> bangdiemList = new ArrayList<BangdiemEntity>();
        String line;
        FileReader fr;
        fc.setDialogTitle("Chọn file để Import");
        File file = null;
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
        }
        if (file == null){
            return null;
        }
        try {
            fr = new FileReader(file.getAbsolutePath());
            br = new BufferedReader(fr);
            String lop = br.readLine();
            String [] splitLop = lop.split("-");
            LophocDao lophocDao = new LophocDao();
            List<LophocEntity> lophoc = lophocDao.readListLopHoc();
            LophocEntity lophocEntity = new LophocEntity(splitLop[0]);
            if (!lophoc.contains(lophocEntity))
                lophocDao.add(lophocEntity);
            while ((line = br.readLine()) != null){
                String[] list = line.split(",");
                if (list.length > 0){
                    BangdiemEntity bangdiemEntity = new BangdiemEntity();
                    bangdiemEntity.setSinhvien(list[0]);
                    bangdiemEntity.setHoten(quanLySinhVienController.findSinhVienByMssv(list[0]).getHoten());
                    bangdiemEntity.setMonhoc(splitLop[1]);
                    bangdiemEntity.setDiemgk(Double.parseDouble(list[1]));
                    bangdiemEntity.setDiemck(Double.parseDouble(list[2]));
                    bangdiemEntity.setDiemkhac(Double.parseDouble(list[3]));
                    bangdiemEntity.setDiemtong(Double.parseDouble(list[4]));
                    if (Double.parseDouble(list[4]) >= 5)
                        bangdiemEntity.setDau((byte) 1);
                    else
                        bangdiemEntity.setDau((byte) 0);
                    bangdiemEntity.setLophoc(lophocEntity);
                    bangdiemList.add(bangdiemEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bangdiemList;
    }
}
