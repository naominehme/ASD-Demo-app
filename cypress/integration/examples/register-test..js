context('Register test', () => {
    beforeEach(() => {
        cy.visit('https://asd-demo-app-249308.appspot.com/Register.html')
    })

        describe('Required Field', () => {

            it('should show missing required field, on send without any input', () => {
                cy.get('button[type=submit]')
                    .click()
            })

        })
        describe('Email', () => {
            it('should show invalid email', () => {
                cy.get('input[name=email]')
                    .type('abc')
                    .blur()
            })
        })
        describe('firstName', () => {
            it('should show invalid first name', () => {
                cy.get('input[name=firstName]')
                    .type('344444')
                    .blur()
            })
        })
        describe('lastName', () => {
            it('should show invalid last name', () => {
                cy.get('input[name=email]')
                    .type('222222')
                    .blur()
            })
        })
        describe('phone', () => {
            it('should show invalid phone', () => {
                cy.get('input[name=phone]')
                    .type('2222222')
                    .blur()
            })
        })
        describe('Register Succeed', () => {
            it('Register Succeed', () => {

            const firstName = 'weixiang';
            const lastName = 'gao';
            const email = '1@gmail.com';
            const phone = '0420000000';
            const DOB = '2011-11-11';
            const streetName = 'PittSt';
            const streetNumber = '12';
            const postcode = '2000';
            const state = 'NSW';
            const username = 'test123';
            const password = '123';

            cy.get('input[name=firstName]').type(firstName);
            cy.get('input[name=lastName]').type(lastName);
            cy.get('input[name=email]').type(email);
            cy.get('input[name=phone]').type(phone);
            cy.get('input[name=DOB]').type(DOB);
            cy.get('input[name=streetName]').type(streetName);
            cy.get('input[name=streetNumber]').type(streetNumber);
            cy.get('input[name=postcode]').type(postcode);
            cy.get('input[name=state]').type(state);
            cy.get('input[name=username]').type(username);
            cy.get('input[name=password]').type(password);
            cy.get('button[type=submit]').click();

            })
        })

})



