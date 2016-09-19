package com.mtsmda.validation.structure.sequence;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Created by MTSMDA on 31.08.2016.
 */
@GroupSequence(value = {Default.class, FirstOrder.class, SecondOrder.class, ThirdOrder.class, FourthOrder.class})
public interface OrderGroupSequence {
}