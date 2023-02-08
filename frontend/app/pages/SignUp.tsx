const SignUpForm = () => {
    const { control, handleSubmit, errors } = useForm();
  
    const onSubmit = (data: any) => {
      console.log(data);
    };
  
    return (
      <View>
        <Controller
          control={control}
          render={({ onChange, onBlur, value }) => (
            <TextInput
              style={{
                padding: 8,
                margin: 8,
                backgroundColor: '#E2E8F0',
                borderRadius: 4,
              }}
              onBlur={onBlur}
              onChangeText={(value) => onChange(value)}
              value={value}
              placeholder="Name"
            />
          )}
          name="name"
          rules={{ required: true }}
          defaultValue=""
        />
        {errors.name && <Text>This is
  