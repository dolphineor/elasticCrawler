package org.spider.filter.rabin;

import org.spider.filter.IFilter;

import java.util.HashSet;

/**
 * Created on 2015-08-29.
 *
 * @author dolphineor
 */
public class RabinHashFilter implements IFilter {

    private int rabinCount = 20;

    private HashSet<Integer> set;

    public RabinHashFilter(int count, int rabinCount) {
        this.set = new HashSet<>(count);
        this.rabinCount = rabinCount;
    }

    @Override
    public float similar(String content) {
        float value = 1;
        RabinHashFunction32 rabin = new RabinHashFunction32(rabinCount);
        int rabinCode = rabin.hash(content);
        if (!set.contains(rabinCode)) {
            value = 0;
            set.add(rabinCode);
        }
        return value;
    }
}
