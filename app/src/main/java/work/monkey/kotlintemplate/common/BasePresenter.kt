package work.monkey.kotlintemplate.common

import androidx.annotation.CallSuper

/**
 * Base class for Presenters
 * raywenderlich
 */
abstract class BasePresenter<M : SomeModel, V : SomeView<M>> : SomePresenter<M, V> {

    /**
     * The View
     */
    protected var view: V? = null

    @CallSuper
    override fun bind(v: V) {
        view = v
    }


    @CallSuper
    override fun unbind() {
        view = null
    }
}