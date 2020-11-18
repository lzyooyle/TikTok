<template>
    <div>
        <el-form
            :rules = "rules"
            ref = "loginForm"
            v-loading = "loading"
            element-loading-text = "login..."
            element-loading-spinner = "el-icon-loading"
            element-loading-background = "rgba(0, 0, 0, 0.8)"
            :model = "loginForm"
            class = "loginContainer">
            <h3 class = "loginTitle">login</h3>
            <el-form-item prop = "username">
                <el-input size = "normal" type = "text" v-model = "loginForm.username" auto-complete = "off"
                          placeholder = "username"></el-input>
            </el-form-item>
            <el-form-item prop = "password">
                <el-input size = "normal" type = "password" v-model = "loginForm.password" auto-complete = "off"
                          placeholder = "password"></el-input>
            </el-form-item>
            <el-form-item prop = "code">
                <el-input size = "normal" type = "text" v-model = "loginForm.code" auto-complete = "off"
                          placeholder = "code" @keydown.enter.native = "submitLogin" style = "width: 250px"></el-input>
                <img :src = "vcUrl" @click = "updateVerifyCode" alt = "" style = "cursor: pointer">
            </el-form-item>
            <el-checkbox size = "normal" class = "loginRemember" v-model = "checked"></el-checkbox>
            <el-button size = "normal" type = "primary" style = "width: 100%" @click = "submitLogin">login</el-button>
        </el-form>
    </div>
</template>
<script>
    export default {
        name: 'Login',
        data() {
            return {
                loading: false,
                vcUrl: '/verifyCode?time='+new Date(),
                loginForm: {
                    username: 'admin',
                    password: '123',
                    code: ''
                },
                checked: true,
                rules: {
                    username: [{required: true, message: 'username', trigger: 'blur'}],
                    password: [{required: true, message: 'password', trigger: 'blur'}],
                    code: [{required: true, message: 'code', trigger: 'blur'}]
                }
            }
        },
        methods: {
            updateVerifyCode() {
                this.vcUrl = '/verifyCode?time='+new Date()
            },
            submitLogin() {
                this.$refs.loginForm.validate((valid) => {
                    if(valid) {
                        this.loading = true;
                        this.postRequest('/doLogin', this.loginForm).then(resp => {
                            this.loading = false;
                            if(resp) {
                                this.$store.commit('INIT_CURRENTHR', resp.obj);
                                window.sessionStorage.setItem("user", JSON.stringify(resp.obj));
                                let path = this.$route.query.redirect;
                                this.$router.replace((path == '/' || path == undefined) ? '/home' : path);
                            }else {
                                this.vcUrl = '/verifyCode?time='+new Date();
                            }
                        })
                    }else {
                        return false;
                    }
                });
            }
        }
    }
</script>
<style>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #eaeaea;
    }
    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }
    .loginRemember {
        text-align: left;
        margin: 0px 0px 15px 0px;
    }

</style>
