package club.wljyes.util;

import club.wljyes.dao.RelationshipDAOImp;

public class Page {
    private int start;
    private int count;
    private int total;

    public Page() {}

    public Page(int start, int count) {
        this.start = start;
        this.count = count;
    }

    public boolean isHasPrevious() {
        if (start == 0)
            return false;
        return true;
    }

    public boolean isHasNext() {
        if (start == getLast())
            return false;
        return true;
    }

    public int getLast() {
        int last = 0;
        if (total % count == 0) {
            last = total - count;
        } else {
            last = total - total % count;
        }
        last = last < 0 ? 0 : last;
        return last;
    }

    public int getTotalPage() {
        int tp = 0;
        if (total % count == 0)
            tp = total / count;
        else
            tp = total / count + 1;
        tp = tp == 0 ? 1 : total;
        return tp;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() { return total; }

    public void setTotal(int total) {
        this.total = total;
    }
}
