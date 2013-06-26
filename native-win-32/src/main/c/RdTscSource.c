#define __ssize_t_defined
#include "ru_smartislav_clock_rdtsc_RdTscSource.h"

static __inline__ unsigned long long rdtsc(void) {
    unsigned hi, lo;
    __asm__ __volatile__ ("rdtsc" : "=a"(lo), "=d"(hi));
    return ( (unsigned long long)lo)|( ((unsigned long long)hi)<<32 );
}

JNIEXPORT jlong JNICALL Java_ru_smartislav_clock_rdtsc_RdTscSource_rdtsc(JNIEnv *env, jclass klass) {
    return (jlong) rdtsc();
}
