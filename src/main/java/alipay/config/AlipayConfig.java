package alipay.config;

/**
 * <pre>
 * ������AlipayConfig
 * ���ܣ�����������
 * ��ϸ�������ʻ��й���Ϣ������·��
 * �汾��3.3
 * ���ڣ�2012-08-10
 * ˵����
 * ���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 * �ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���
 * 	
 * ��ʾ����λ�ȡ��ȫУ����ͺ��������ID
 * 1.������ǩԼ֧�����˺ŵ�¼֧������վ(www.alipay.com)
 * 2.������̼ҷ���(https://b.alipay.com/order/myOrder.htm)
 * 3.�������ѯ���������(PID)��������ѯ��ȫУ����(Key)��
 * 
 * ��ȫУ����鿴ʱ������֧�������ҳ��ʻ�ɫ��������ô�죿
 * ���������
 * 1�������������ã������������������������
 * 2���������������ԣ����µ�¼��ѯ��
 * </pre>
 */

public class AlipayConfig {
    
    //�����������������������������������Ļ�����Ϣ������������������������������
    // ���������ID����2088��ͷ��16λ��������ɵ��ַ���
    public static final String PARTNER = "2088021272648125";
    
    /********************************** seller_id>seller_account_name>seller_email */
    // �տ�֧�����˺�
    public static final String SELLER_EMAIL = "company@cndwine.com";
    
    // �տ�֧�����û���
    public static final String SELLER_ID = "2088021272648125";
    
    // �տ����֧�����˺�
    public static final String SELLER_ACCOUNT_NAME = "";
    
    /**********************************       */
    
    // �̻���˽Կ
    public static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANuL88MoX+8uF9NECsvngdhVocHRLsWxe06CcawfazxYR5c+4Ps89emCZ5BXqlRjB3aME9qtFX1ZHmxj3vphj1cJcysipu4KNrur1fMVPjy9lWofNMzi8M3n2RfTepnuc4QaOjdI2fZsxlH/nmxl8xvO/4jNoEkijcDQnxE2BKrbAgMBAAECgYEAosgR5RF6kAzM/YfluWndjFlxphNDarE9i+iL/+MLz6Jk8m9cwOtiKdDWmN9nG0qRrgQOdIUA+f+p3xPtsBfF/pKfyEUhnpcZmhVEYm8t8NNQI9EC1pdc7kPpbgokQSPvm/jUTpzplH8s77ZJ+IpSTIG6pXrhfb2wHJ5WxMAhSYECQQD6Rz3wt+RTznPo3HJt8VWbmK2GYyh7DVtTMu/ZgCENJ8lqZlGdMVBZ6tDbDbmJMT1cnfhE66k71K3fydglor9fAkEA4JDaXyW5lmq39mbgDiMCRMuj5JBb3AbSP+SxGoDyDR/lodVgDv92vR+JDKi6gqys3GKJwupxYB4A6PnKRAXSBQJASUACm+pmVDTvba7fetgQUyrbVqK83U7qdK5KzqCWi9AiPhbWOiiNuNNl70bUD5guumWtnRZQAKfdf8sGjPVWBwJBAJZahcrksEB0jV0NfgAZEoDLM51pxgcoDgc3cBiFswp0i8Px4BHKm05+2by/D7lEMayQ0RnO7D/ldsiFzhYDK50CQQCvrMlbl/Oy+SgSrw/8GVp4cDMCmp7uZ2yYupt0XqqjZSHwAeUb0rCuZI8YMntbHEc2Pp8OX22Zz/47BpVBtbpM";
    
    // ֧�����Ĺ�Կ
    public static String ALI_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    
    //�����������������������������������Ļ�����Ϣ������������������������������
    
    // �����ã�����TXT��־�ļ���·��
    public static final String LOG_PATH = "F:/temp";
    
    // �ַ������ʽ Ŀǰ֧�� gbk �� utf-8
    public static final String INPUT_CHARSET = "gbk";
    
    // ǩ����ʽ �����޸�(DSA��RSA��MD5)
    public static final String SIGN_TYPE = "MD5";
    
    //�ӿڷ���--��ʱ���˽��׽ӿ�
    public static final String SERVICE = "create_direct_pay_by_user";
    
    //֧������----��1����Ʒ���͹���4��������47�����ӿ�ȯ��
    public static final String PAYMENT_TYPE = "1";
    
    //�������첽֪ͨҳ��·��
    public static final String NOTIFY_URL = "http://127.0.0.1:8080/zhifubao/alipay/asyncNotify";
    
    //ҳ����תͬ��֪ͨҳ��·��
    public static final String RETURN_URL = "http://127.0.0.1:8080/zhifubao/alipay/syncNotify";
    
    //��Ʒչʾ��ַ
    public static final String SHOW_URL = "http://127.0.0.1:8080/zhifubao/show.jsp";
    
    //�������첽֪ͨ����ҳ��·�����迪ͨ�˹��ܣ�
    public static final String ERROR_NOTIFY_URL = "";
    
    //�ͻ��˵�IP��ַ
    public static final String EXTER_INVOKE_IP = "127.0.0.1";
    
    //Ĭ��֧����ʽ(creditPay������֧����directPay�����֧����Ĭ�ϣ�)
    public static final String PAYMETHOD = "directPay";
    
}
