package work.monkey.kotlintemplate.common

/**
 * raywenderlich
 */
interface SomePresenter<M : SomeModel, V : SomeView<M>> {

    /**
     * Binds the view to the Presenter
     */
    fun bind(v: V)

    /**
     * Unbinds the View
     */
    fun unbind()
}