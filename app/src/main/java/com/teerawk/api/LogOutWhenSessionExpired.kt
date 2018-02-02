package com.teerawk.api


//class LogOutWhenSessionExpired(private val mContext: Context) : Function<Observable<out Throwable>, Observable<Any>> {
//
//    override fun apply(observable: Observable<out Throwable>): Observable<Any> {
//        return observable.observeOn(AndroidSchedulers.mainThread()).flatMap(Function<Throwable, Observable<*>> { throwable ->
//            if (throwable is HttpException) {
//                val errorBody = ErrorBody.parseError(throwable.response())
//                if (errorBody != null) {
//                    val code = errorBody.code
//                    if (code == ErrorBody.INVALID_TOKEN) {
//                        return@Function Completable.complete().observeOn(AndroidSchedulers.mainThread()).doOnComplete {
//                            Toast.makeText(mContext, R.string.your_session_expired, Toast.LENGTH_SHORT).show()
//                            logOut()
//                        }.toObservable<Any>()
//                    }
//                }
//            }
//            // If cannot be handled - pass the original error through.
//            Observable.error<Any>(throwable)
//        })
//    }
//
//    fun logOut() {
//      //  SharedPrefManager.clear()
//        //ReposRepository().clear()
//    }
//}
