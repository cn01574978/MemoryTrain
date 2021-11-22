package com.xyys.memorytrain.bean;

/**
 * @ProjectName: MemoryTrain
 * @Package: com.xyys.memorytrain.bean
 * @ClassName: TrainData
 * @Description: 训练数据类，封装了训练时需要的一些数据
 * @Author: 完美中正
 * @CreateDate: 2021/11/21 20:13
 * @Version: 1.0
 */
public class TrainData {
    /**
     * 训练数据ID Manager
     */
    private String mTrainID;
    /**
     * 训练数据名称
     */
    private String mTrainName;
    /**
     * 玩法ID
     * 例如：数字训练=1、英文训练=2、等
     */
    private String mTrainTypeID;
    /**
     * 玩法名称
     * 例如：数字训练、英文训练、等
     */
    private String mTrainTypeName;
    /**
     * 源数据
     * 可以是字符串 例如：1、2、01、a、b、c、或汉子等
     * 也可以是路径，例如扑克 A、2、3等
     */
    private String mSourceData;
    /**
     * 默认编码
     * 在没有自定义的使用使用
     */
    private encoded mDefaultEncoded;
    /**
     * 主编码
     * 自定义的主要编码
     */
    private encoded mMainEncoded;
    /**
     * 次编码
     * 自定义的次要编码
     */
    private encoded mMnorEncoded;



    /**
     * 编码类
     * 包含两属性
     */
    public static class encoded {
        /**
         * 编码路径
         */
        String encoded_path;
        /**
         * 编码信息
         */
        String encoded_info;

        public encoded(String encoded_path, String encoded_info) {
            this.encoded_path = encoded_path;
            this.encoded_info = encoded_info;
        }
    }
}
