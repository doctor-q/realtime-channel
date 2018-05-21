package cc.doctor.data.reader;

import java.util.Collections;
import java.util.List;

/**
 * 链式过滤
 * Condition: 每个filter都会且仅会生成一个输出
 */
public class ReaderFilterChain implements ReaderFilter {
    private List<ReaderFilter> readerFilters;

    @Override
    public Iterable filter(Object object) {
        for (ReaderFilter readerFilter : readerFilters) {
            Iterable iterable = readerFilter.filter(object);
            if (!iterable.iterator().hasNext()) {
                return null;
            } else {
                object = iterable.iterator().next();
            }
        }
        return Collections.singleton(object);
    }
}
