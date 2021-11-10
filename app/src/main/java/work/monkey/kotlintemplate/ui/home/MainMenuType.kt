package work.monkey.kotlintemplate.ui.home

import work.monkey.kotlintemplate.R

enum class MainMenuType(val position: Int,
                        val viewType: MainMenuViewType,
                        val text: String,
                        val resourceId: Int) {
    MONKEY_TYPES(
            position = 1,
            viewType = MainMenuViewType.ITEM_HEADER,
            text = "Monkey types",
            resourceId = -1
    ),
    MANDRILL(
            position = 2,
            viewType = MainMenuViewType.ITEM,
            text = "Mandrill",
            resourceId = R.drawable.ic_baseline_success_24
    ),
    CAPUCHIN_MONKEY(
            position = 3,
            viewType = MainMenuViewType.ITEM,
            text = "Capuchin",
            resourceId = R.drawable.ic_baseline_login_24
    ),
    MARMOSET(
        position = 3,
        viewType = MainMenuViewType.ITEM,
        text = "Marmoset",
        resourceId = R.drawable.ic_baseline_manage_accounts_24
    ),
    SAPIENS(
        position = 1,
        viewType = MainMenuViewType.ITEM_HEADER,
        text = "Sapiens",
        resourceId = -1
    ),
    GAUTENGENSIS(
        position = 0,
        viewType = MainMenuViewType.ITEM,
        text = "Gautengensis",
        resourceId = R.drawable.ic_baseline_info_24
    ),
    CEPRANENSIS(
        position = 0,
        viewType = MainMenuViewType.ITEM,
        text = "Cepranensis",
        resourceId = R.drawable.ic_baseline_warning_24
    );
}