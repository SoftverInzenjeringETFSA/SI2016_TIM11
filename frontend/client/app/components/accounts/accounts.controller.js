class AccountsController {
	static $inject = ['accountService', 'swalService', 'roleService'];

	constructor(accountService, swalService, roleService) {
		this.accountService = accountService;
		this.swalService = swalService;
		this.roleService = roleService;

		// Filters are disabled by first
		this.searchText = '';

		this.setEmptyAccount();

		this.roleService.all().then(response => {
			this.roles = response.data;

			this.loadAccounts();
		});
	}

	refresh() {
		if (this.searchText) {
			this.filter();
		} else {
			this.loadAccounts();
		}
	}

	loadAccounts(page = 1) {
		this.accountService.getPage(page).then(response => {
			this.accounts = response.data.content;
			this.number = response.data.number + 1;
			this.totalPages = response.data.totalPages;
		})
	}

	resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyAccount();
    }

    setEmptyAccount() {
    	this.account = {
    		id: null,
    		fullName: '',
    		username: '',
    		email: '',
    		password: '',
    		roleId: null
    	};
    }

    saveAccount() {
    	if (!this.form.$valid) {
    		return;
    	}

    	if (this.account.id) {
    		this.updateAccount();
    	} else {
    		this.createAccount();
    	}
    }

    createAccount() {
    	this.accountService.create(this.account).then(response => {
    		this.refresh();
    		this.closeModal();

    		this.swalService.success('Nova kategorija je uspješno kreirana.');
    	}, error => {});
    }

    updateAccount() {
    	this.accountService.update(this.account.id, this.account).then(response => {
    		this.refresh();
    		this.closeModal();

    		this.swalService.success('Izmjene su uspješno sačuvane.');
    	}, error => {});
    }

    edit(id) {
    	this.accountService.find(id).then(response => {
    		this.account = {
    			id: response.data.id,
    			fullName: response.data.fullName,
    			username: response.data.username,
    			email: response.data.email,
    			password: null,
    			roleId: response.data.role.id
    		};

    		this.openModal();
    	})
    }

    delete(id) {
    	this.swalService.areYouSure('Obrisani korisnički račun se ne može vratiti.', () => {
    		this.refresh();

    		this.swalService.success('Korisnički račun je uspješno obrisan.');
    	});
    }

    closeModal() {
    	$('#user-modal').modal('close');
    }

    openModal() {
    	$('#user-modal').modal({
    		complete: () => this.resetForm()
    	}).modal('open');
    }

    goto(newPage) {
    	if (newPage > 0 && newPage <= this.totalPages) {
    		this.loadAccounts(newPage);
    	}
    }

    filter() {
    	this.accountService.filterByEmail(this.searchText).then(response => {
    		this.accounts = response.data;
    	});
    }
}

export default AccountsController;
