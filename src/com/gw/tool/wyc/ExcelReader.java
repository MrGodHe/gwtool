package com.gw.tool.wyc;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class ExcelReader {

//    private static Logger logger = LoggerFactory.getLogger(ExcelReader.class);

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    private static final String path = "D:\\tmp\\excel\\result\\";
    private static final String templateFileName = "D:\\tmp\\excel\\template.xlsx";
    private static final String accountOrderDate = "2020年6月1日至2020年6月30日";

    public static void main(String[] args) {
        // 设定Excel文件所在路径
        String excelFileName = "D:\\tmp\\excel\\网约车订单202006.xlsx";
        readExcel(excelFileName);
    }

    /**
     * 读取Excel文件内容
     *
     * @param fileName 要读取的Excel文件所在路径
     * @return 读取结果列表，读取失败时返回null
     */
    public static void readExcel(String fileName) {

        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

            // 获取Excel文件
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
//                logger.info("指定的Excel文件不存在！");
                return;
            }
            // 获取Excel工作簿
            inputStream = new FileInputStream(excelFile);
            workbook = getWorkbook(inputStream, fileType);

            parseExcel(workbook);

        } catch (IOException e) {
//            logger.info("读取Excel文件异常！", e);
        }
    }

    public static void parseExcel(Workbook workbook) {

        Map<String, List<AccountData>> map = new HashMap<>();

        Sheet sheet = workbook.getSheetAt(0);
        // 获取第一行数据
        int firstRowNum = sheet.getFirstRowNum();

        // 解析每一行的数据，构造数据对象
        int rowStart = firstRowNum + 2;
        int rowEnd = sheet.getPhysicalNumberOfRows();
        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {

            Row row = sheet.getRow(rowNum);
            AccountData data = convertRow(row); //行转换

            //分类统计
            List<AccountData> dList = map.get(data.getOrgName());
            if(isEmpty(dList)){
                dList = new ArrayList<>();
            }
            dList.add(data);
            map.put(data.getOrgName(), dList);
        }


        //导出报表
        map.forEach((k, v) -> {
            toExcelFile(k,v);
        });
    }

    public static void toExcelFile(String key,  List<AccountData> list){
        try {
            Map<String, Object> model = new HashMap<>(100);
            model.put("list", list);
            model.put("orgName", key);
            model.put("dateStr", accountOrderDate);
            countTotal(model, list);

            //模板文件
            File template = new File(templateFileName);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(template));

            //执行模板
            XLSTransformer transformer = new XLSTransformer();
            Workbook workbook = transformer.transformXLS(inputStream, model);
            workbook.getSheetAt(0).setForceFormulaRecalculation(true);

            //输出文件
            String fileName = path + key + ".xlsx";
            FileOutputStream outStream = new FileOutputStream(new File(fileName));
            workbook.write(outStream);
            outStream.close();
            inputStream.close();

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转化每一行
     */
    public static AccountData convertRow(Row row) {

        AccountData data = new AccountData();

        data.setOrderTime(convertCellValueToString(row.getCell(0)));
        data.setPayTime(convertCellValueToString(row.getCell(25)));
        data.setOrderNo(convertCellValueToString(row.getCell(26)));
        data.setDepartName(convertCellValueToString(row.getCell(2)));
        data.setUserName(convertCellValueToString(row.getCell(4)));
        data.setType(convertCellValueToString(row.getCell(3)));
        data.setReason(convertCellValueToString(row.getCell(14)));
        data.setStartAddress(convertCellValueToString(row.getCell(17)));
        data.setEndAddress(convertCellValueToString(row.getCell(18)));
        data.setServerName(convertCellValueToString(row.getCell(15)));

        //里程积分
        String licheng = convertCellValueToString(row.getCell(19));
        data.setLicheng(new BigDecimal(licheng));
        String shichang = convertCellValueToString(row.getCell(11));
        data.setShichang(Long.valueOf(shichang));

        //订单金额
        String orderAmount = convertCellValueToString(row.getCell(20));
        data.setOrderAmount(new BigDecimal(orderAmount));

        //订单折后金额
        String selfAmount = convertCellValueToString(row.getCell(21));
        BigDecimal sAmount = new BigDecimal(selfAmount);
        data.setSelfAmount(sAmount);

        //服务费
        //服务费 = 订单折后金额*10%
        BigDecimal svAmount = sAmount.multiply(new BigDecimal("0.1")).setScale(2, BigDecimal.ROUND_HALF_UP);
        data.setSeverAmount(svAmount);

        //结算金额
        //结算金额 = 订单折后金额+服务费
        BigDecimal actAmount = sAmount.add(svAmount);
        data.setActAmount(actAmount);

        data.setActState("待结算");
        data.setOrgName(convertCellValueToString(row.getCell(1)));

        return data;
    }

    /**
     * 将单元格内容转换为字符串
     *
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String returnValue = cell.getStringCellValue();
        /*switch (cell.getCellType()) {
            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();
                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串

                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }*/
        return returnValue;
    }

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     *
     * @param inputStream 读取文件的输入流
     * @param fileType    文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType)
            throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 汇总统计
     *
     * @param model
     * @param list
     */
    public static void countTotal(Map<String, Object> model, List<AccountData> list){
        if(isEmpty(list)){
            return;
        }

        BigDecimal totalLicheng = new BigDecimal(0);
        BigDecimal totalOrderAmount = new BigDecimal(0);
        BigDecimal totalSelfAmount = new BigDecimal(0);
        BigDecimal totalSeverAmount = new BigDecimal(0);
        BigDecimal totalActAmount = new BigDecimal(0);

        for(AccountData data : list){
            //汇总里程
            BigDecimal licheng = (data.getLicheng() == null ? new BigDecimal(0) : data.getLicheng());
            totalLicheng = totalLicheng.add(licheng);
            //汇总订单金额
            BigDecimal orderAmount  = (data.getOrderAmount() == null? new BigDecimal(0): data.getOrderAmount());
            totalOrderAmount = totalOrderAmount.add(orderAmount);
            //汇总折扣金额
            BigDecimal selfAmount = (data.getSelfAmount() == null? new BigDecimal(0) : data.getSelfAmount());
            totalSelfAmount = totalSelfAmount.add(selfAmount);
            //汇总服务费
            BigDecimal severAmount = (data.getSeverAmount() == null ? new BigDecimal(0) : data.getSeverAmount());
            totalSeverAmount = totalSeverAmount.add(severAmount);
            //汇总结算金额
            BigDecimal actAmount = (data.getActAmount() == null ? new BigDecimal(0) : data.getActAmount());
            totalActAmount = totalActAmount.add(actAmount);
        }
        model.put("totalLicheng", totalLicheng);
        model.put("totalOrderAmount", totalOrderAmount);
        model.put("totalSelfAmount", totalSelfAmount);
        model.put("totalSeverAmount", totalSeverAmount);
        model.put("totalActAmount", totalActAmount);
    }

    public static String formatDecimal (double value) {

        //DecimalFormat df = new DecimalFormat("#0.00"); 最近取舍

        //直接舍掉小数后位数
        final DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        return formater.format(value);

    }

    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }













    private static final List<String> titles =
            Arrays.asList("下单时间",
                    "支付时间",
                    "订单号",
                    "部门",
                    "用户",
                    "用车类型",
                    "用车事由",
                    "出发地",
                    "目的地",
                    "服务商",
                    "里程（公里）",
                    "时长（分）",
                    "原订单金额（元）",
                    "订单折后金额（元）",
                    "服务费（元）",
                    "结算金额（元）",
                    "结算状态"
            );
}
