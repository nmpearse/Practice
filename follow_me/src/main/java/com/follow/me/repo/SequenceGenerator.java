package com.follow.me.repo;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceGenerator {

	private AtomicLong value = new AtomicLong(1);

    public long getNext() {
        return value.getAndIncrement();
    }
}
