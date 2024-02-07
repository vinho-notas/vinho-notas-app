const Login = () => {
  return (
    <form>
        <h1>Login</h1>        
        <div>
            <input type="email" placeholder="youre-email@email.com"/>
            <label>Email address</label>
        </div>
        <div>
            <input type="password" placeholder="password"/>
            <label>Password</label>
        </div>
        <div>
            <input type="checkbox"/>
            <label>Remember me</label>
        </div>
        <button type="submit">Sign in</button>
    </form>
  )
}

export default Login