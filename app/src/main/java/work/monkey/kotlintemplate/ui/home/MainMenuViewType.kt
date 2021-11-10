package work.monkey.kotlintemplate.ui.home

enum class MainMenuViewType(val id: Int) {
    ITEM_HEADER(id = 0),
    ITEM(id = 1);

    companion object {
        fun from(viewType: Int) = MainMenuViewType.values().first { it.id == viewType }
    }
}