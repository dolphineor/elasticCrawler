package org.spider.filter;

/**
 * Created on 2015-08-29.
 *
 * @author dolphineor
 */
@FunctionalInterface
public interface IFilter {

    /**
     * <p>判断内容的相似性, 为1表示已经存在, 为0表示不存在, 为浮点数则判断相似度的值.
     *
     * @param content 网页内容
     * @return similarity
     */
    float similar(String content);
}
